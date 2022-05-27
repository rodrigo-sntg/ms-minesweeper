import React, {  useRef, useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import './styles.css';
import api from '../../services/api.ts';
import './styles.css';
import PageHeader from '../../components/PageHeader';
import useWebSocket from 'react-use-websocket';

const URL = 'ws://localhost:8080/chat/';

function reconnectingSocket(url) {
    let client;
    let isConnected = false;
    let reconnectOnClose = true;
    let messageListeners = [];
    let stateChangeListeners = [];
  
    function on(fn) {
      messageListeners.push(fn);
    }
  
    function off(fn) {
      messageListeners = messageListeners.filter(l => l !== fn);
    }
  
    function onStateChange(fn) {
      stateChangeListeners.push(fn);
      return () => {
        stateChangeListeners = stateChangeListeners.filter(l => l !== fn);
      };
    }
  
    function start() {
      client = new WebSocket(URL);
  
      client.onopen = () => {
        isConnected = true;
        stateChangeListeners.forEach(fn => fn(true));
      }
  
      const close = client.close;
  
      // Close without reconnecting;
      client.close = () => {
        reconnectOnClose = false;
        close.call(client);
      }
  
      client.onmessage = (event) => {
        messageListeners.forEach(fn => fn(event.data));
      }
  
      client.onerror = (e) => console.error(e);
  
      client.onclose = () => {
  
        isConnected = false;
        stateChangeListeners.forEach(fn => fn(false));
  
        if (!reconnectOnClose) {
          console.log('ws closed by app');
          return;
        }
  
        console.log('ws closed by server');
  
        setTimeout(start, 3000);
      }
    }
  
    start();
  
    return {
      on,
      off,
      onStateChange,
      close: () => client.close(),
      getClient: () => client,
      isConnected: () => isConnected,
    };
  }
  
  
const client = reconnectingSocket(URL);

function useMessages() {
    const [messages, setMessages] = useState([]);
  
    useEffect(() => {
      function handleMessage(message) {
        setMessages([...messages,JSON.parse(message)]);
      }
      client.on(handleMessage);
      return () => client.off(handleMessage);
    }, [messages, setMessages]);
  
    return messages;
  }

function Game() {
    const { code } = useParams();
    const [game, setGame] = useState(undefined);
    const [message, setMessage] = useState('');
    const messages = useMessages();
    const [isConnected, setIsConnected] = useState(client.isConnected());


    useEffect(() => {
        return client.onStateChange(setIsConnected);
      }, [setIsConnected]);
    
      useEffect(() => {
        if (isConnected) {
          client.getClient().send('hi');
        }
      }, [isConnected]);
    
      function sendMessage(e) {
        e.preventDefault();
        client.getClient().send(message);
        setMessage('');
      }

    console.log(code);
    const [counter, setCounter] = useState(0);
        
    useEffect(() => {
        api.get('game/' + code + '/code').then((res) => {
            console.log(res);
            setGame(res.data);
            
        }).catch((err) => {
            console.log(err);
        });
        
    }, [counter]);



    function play(cell) {
        const userPlay = {
            "boardCode": code,
            "row": cell.row,
            "col": cell.col,
            "isFlagging": false
        }
        api.post('game/play', userPlay).then((res) => {
            setCounter(counter + 1);
        }).catch((err) => {
            console.log(err);
        })
        
        console.log(cell)

        
    };


    function getDifficulty(key){

        switch (key) {
            case 1:
                return 'Private';
            case 2:
                return 'Sargeant';
            case 3:
                return 'Major';
            case 4:
                return 'Chuck Norris';
            case 5:
                return 'Custom';
        
            default:
                break;
        }

    }

    return (
        <div id="game" className="container">
            <PageHeader title="Let's Play!" 
                description="Click on desired cells to open it."/>
                <main>
                    { game !== undefined ? (
                        <div > 
                            <h2 className="game-player">Bem vindo {game.player}</h2>
                            <div className="matrix-container">
                                <div className='matrix-panel'>
                                    {game.state}
                                    <div><span><b>Rows:</b>  </span><span>{game.board.numRows}</span></div>
                                    <div><span><b> Columns: </b> </span><span>{game.board.numColumns}</span></div>
                                    <div><span><b>Difficulty:</b> </span><span> {getDifficulty(game.difficulty)}</span></div>
                                </div>
                                <div>
                                    <div className="matrix">
                                        {game.board.cells.map((row, i) => (
                                        <div className="matrix-row" key={i}>
                                            {row.map((col, j) => (
                                            <span onClick={() => play(col)} className={`matrix-col co-${col.surfaceValue}`} key={j}>{col.surfaceValue}</span>
                                            ))}
                                        </div>
                                        ))}
                                    </div>
                                </div>
                            </div>

                            <div>
                                <div className="messages">
                                    {messages.map((row, i) => (
                                        <div className="message-block" key={i}>
                                            <span className="user">{row.user}:</span>
                                            <span className='message'>{row.message}</span>
                                        </div>
                                    ))}
                                </div>
                                <form onSubmit={sendMessage}>

                                    <input
                                        type="text"
                                        placeholder='Digite a mensagem'
                                        value={message} 
                                        onChange = {(e) => setMessage(e.target.value)}
                                    />

                                    <button type="submit">
                                        Send
                                    </button>
                                </form>

                            </div>
                        </div>


                    ) : ''

                    }
                </main>
        </div>
    )
}


export default Game;
