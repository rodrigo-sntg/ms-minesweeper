import React, { PureComponent, useState, FormEvent } from 'react';


import './styles.css';
import api from '../../services/api.ts';



import './styles.css';
import PageHeader from '../../components/PageHeader';
import Input from '../../components/Input';

import TextArea from '../../components/TextArea';
import Select from '../../components/Select';
import Button from '../../components/Button';
import { useNavigate } from "react-router-dom";

function NewGameForm() {
    const navigate = useNavigate();
    const [player, setPlayer] = useState('');
    const [difficulty, setDifficulty] = useState("1");
    const [bombs, setBombs] = useState(0);
    const [rows, setRows] = useState(10);
    const [columns, setColumns] = useState(10);
    
    const isCustom = difficulty === "5" ? true : false;

    const isFormValid = () => {
        console.log(player != "" && (difficulty != "" && difficulty > 0) && rows > 0 && columns > 0);
      
        return player != "" && (difficulty != "" && difficulty > 0) && rows > 0 && columns > 0
    }

    function handleCreateClass(e: FormEvent){
        e.preventDefault();
        if(isFormValid()){

            api.post('game', {
                player,
                difficulty: Number(difficulty),
                bombs: Number(bombs),
                rows: Number(rows),
                columns: Number(columns),
            }).then((res) => {
                console.log(res);
                navigate('/game/' + res.data.code )
                alert('Game initialized');
            }).catch(() => {
                alert('Error initializing game.')
            })
        }
    }

    return (
        <div id="new-game-form" className="container">
            <PageHeader title="Let's setup your game." 
                description="Tell us your username."/>

                <main>
                    <form onSubmit={handleCreateClass}>

                        <fieldset className="fieldset">
                            <legend>Player</legend>

                            <Input 
                                name="playername" 
                                label="Player Name"
                                value={player}
                                onChange={(e) => { setPlayer(e.target.value)}}
                            />
                        </fieldset>

                        <fieldset className="fieldset">
                            <legend>Difficulty</legend>

                            <Select 
                                name="difficulty" 
                                label="Difficulty Level"
                                value={difficulty}
                                onChange={(e) => { setDifficulty(e.target.value)}}
                                options={[
                                    {value: "1", label: 'Private'},
                                    {value: "2", label: 'Sargeant'},
                                    {value: "3", label: 'Major'},
                                    {value: "4", label: 'Chuck Norris'},
                                    {value: "5", label: 'Custom'}
                                ]}
                                />
                            {isCustom ? (
                                <Input 
                                    name="bombs" 
                                    label="Number of bombs"
                                    value={bombs}
                                    onChange={(e) => { setBombs(e.target.value)}}
                                />
                            ) : ''}
                            
                        </fieldset>

                        <fieldset className="fieldset">
                            <legend>Field Settings</legend>

                            <Input 
                                name="rows" 
                                label="Number of rows"
                                value={rows}
                                onChange={(e) => { setRows(e.target.value)}}
                            />

                            <Input 
                                name="columns" 
                                label="Number of columns"
                                value={columns}
                                onChange={(e) => { setColumns(e.target.value)}}
                            />
                            
                        </fieldset>

                        <footer>
                            <p>
                                Important! <br/>
                                Fill all the fields.
                            </p>
                            <Button disabled={!isFormValid()}>Start Game</Button>
                        </footer>

                    </form>
                </main>
        </div>
    )
}


export default NewGameForm;
