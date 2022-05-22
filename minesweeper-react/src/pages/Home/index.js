import React, { PureComponent, useState, useEffect } from 'react';

import logoImg from '../../assets/images/minesweeper.svg';
import landingImg from '../../assets/images/landing.png';

import studyIcon from '../../assets/images/icons/study.svg';
import giveClassesIcon from '../../assets/images/icons/give-classes.svg';
import purpleHeartIcon from '../../assets/images/icons/purple-heart.svg';

import './styles.css';
import { Link } from 'react-router-dom';
import api from '../../services/api.ts';

function Home() {
    const [totalConnections, setTotalConnections] = useState(0);

    useEffect(() => {
        api.get('game/BMme1X/code').then(res => {
            const {total} = res.data;

            setTotalConnections(total);
        }).catch(err => {

        });
    }, []);

    return (
        <div id="page-landing">
            <div id="page-landing-content" className="container">
                <div className="logo-container">
                    {/* <img src={logoImg} alt="Proffy"/> */}
                    <h1 className="logo-title">MINESWEEPER</h1>
                    <h2>Watch out your steps!</h2>
                </div>
            
                <img src={landingImg} alt="Plataforma de estudos" className="hero-image"/>

                <div className="buttons-container">
                    <Link to="/new-game" className="new-game">
                        New Game
                    </Link>

                    <Link to="/continue" className="give-classes">
                        Continue
                    </Link>
                </div>

            </div>

        </div>
    )
}


export default Home;
