import React from 'react';
import logoImg from '../../assets/images/minesweeper.png';
import backImg from '../../assets/images/icons/back.svg';
import { Link } from 'react-router-dom';

import './styles.css';

interface PageHeaderProps {
    title: string;
    description?: string;
}

const PageHeader: React.FC<PageHeaderProps> = (props) => {
    return (
            <header className="page-header">
                <div className="top-bar-container">
                    <Link to="/">
                        <img src={backImg} alt="Voltar"/>
                    </Link>
                    <img src={logoImg} alt="Proffy"/>
                </div>
                <div className="header-content">
                    <strong>{props.title}</strong>
                    {props.description &&  <p>{props.description}</p>}
                    {props.children}
                </div>

            </header>
    );
}

export default PageHeader;