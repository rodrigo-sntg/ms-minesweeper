import React, { PureComponent, useState, FormEvent } from 'react';
// import  { redirect, history } from 'react-router-dom'
import { useNavigate } from "react-router-dom";



import './styles.css';
import api from '../../services/api.ts';



import './styles.css';
import PageHeader from '../../components/PageHeader';
import Input from '../../components/Input';

import TextArea from '../../components/TextArea';
import Select from '../../components/Select';
import Button from '../../components/Button';

function ContinueGameForm() {
    const navigate = useNavigate();

    const [code, setCode] = useState('');
    

    const isFormValid = code != "";

    const handleContinue = () => {
        navigate('/game/' + code )
        // const local = 'game/' + code + '/code';
        // return <Redirect to={local}  />

        // if(isFormValid){
        //     api.get('game/' + code + '/code').then((res) => {
        //         console.log(res);
        //         alert('Game initialized');
        //     }).catch((err) => {
        //         console.log(err);
        //         alert('Error initializing game.')
        //     })
        // }
    }

    return (
        <div id="continue-game-form" className="container">
            <PageHeader title="Let's continue your game." 
                description="Put the code of the game you want to continue."/>

                <main>
                    <form onSubmit={handleContinue}>

                        <fieldset className="fieldset">
                            <legend></legend>

                            <Input 
                                name="code" 
                                label="Game Code"
                                value={code}
                                onChange={(e) => { setCode(e.target.value)}}
                            />
                        </fieldset>


                        <footer>
                            <p>
                                Important! <br/>
                                Fill all the fields.
                            </p>
                            <Button disabled={!isFormValid}>Continue Game</Button>
                        </footer>

                    </form>
                </main>
        </div>
    )
}


export default ContinueGameForm;
