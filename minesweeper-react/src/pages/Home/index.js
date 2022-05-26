import React from 'react';

import landingImg from '../../assets/images/landing.png';

import './styles.css';
import { Link } from 'react-router-dom';
function Home() {

    return (
        <div id="page-landing">
            <div id="page-landing-content" className="container">
                <div className="logo-container">
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
