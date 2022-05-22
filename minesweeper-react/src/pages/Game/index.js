import React, { PureComponent, useState, FormEvent, useEffect } from 'react';
import {
    useParams
  } from "react-router-dom";


import './styles.css';
import api from '../../services/api.ts';



import './styles.css';
import PageHeader from '../../components/PageHeader';
import Input from '../../components/Input';

import TextArea from '../../components/TextArea';
import Select from '../../components/Select';
import Button from '../../components/Button';

function Game() {
    const { code } = useParams();
    const [game, setGame] = useState(undefined);

    console.log(code);
    const [counter, setCounter] = useState(0);
        
    useEffect(() => {
        api.get('game/' + code + '/code').then((res) => {
            console.log(res);
            setGame(res.data);
            
        }).catch((err) => {
            console.log(err);
        })
    }, [counter]);


    const isFormValid = code != "";

    function handleCreateClass(e: FormEvent){
        e.preventDefault();
        // if(isFormValid){
        // }

        
    }

    /**
     * {
        "boardCode": "ff24rW",
        "row": 4,
        "col": 1,
        "isFlagging": false
        }
     */
    function play(cell) {
        const userPlay = {
            "boardCode": code,
            "row": cell.row,
            "col": cell.col,
            "isFlagging": false
        }
        api.post('game/play', userPlay).then((res) => {
            setCounter(counter + 1);
            // useEffect(() => {
            //     api.get('game/' + code + '/code').then((res) => {
                    // console.log(res);
                    // setGame(res.data);
                    
            //     }).catch((err) => {
            //         console.log(err);
            //     })
            // }, []);
            
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
                    { game != undefined ? (
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
                                            <span onClick={() => play(col)} className="matrix-col" key={j}>{col.surfaceValue}</span>
                                            ))}
                                        </div>
                                        ))}
                                    </div>
                                </div>
                            </div>
                        </div>

                    ) : ''

                    }
                </main>
        </div>
    )
}


export default Game;
