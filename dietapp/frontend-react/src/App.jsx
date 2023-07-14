import './App.css';
import React, {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {logout} from "./components/Services/HttpService";
import 'bulma/css/bulma.min.css';
import 'react-toastify/dist/ReactToastify.css'
import 'react-toastify/dist/ReactToastify.css'

export default function App() {
        const navigate = useNavigate();
        const [isLogged, setIsLogged]=useState(false)
        const clickHandler = () => {
                toggleDropdown()
                logout()
                navigate('/login')
        }
        useEffect(() => {
                if(localStorage.getItem('username')!= null){
                        setIsLogged(true)
                }else{
                        setIsLogged(false)
                }
        },[])
        function toggleDropdown() {
                const myNav = document.querySelector("#my-nav")
                const burger = document.querySelector("#burger")
                myNav.classList.toggle("is-active")
                burger.classList.toggle("is-active")
        }
        return (
            <div>
            <nav className="navbar is-light" role="navigation" aria-label="main navigation">
                    <div className="navbar-brand">
                            <Link to="/" className="navbar-item pb-0 pt-0 mt-0 ">
                                    <h1 style={{color:"#00d1b2"}} className="has-text-weight-bold is-size-3 mb-1">:</h1>
                                    <h1 style={{color:"#00d1b2"}} className="has-text-weight-bold is-size-4">D</h1>
                                    <h1 className="is-size-4"> ietapp</h1>
                            </Link>
                            <button id="burger" className="navbar-burger" onClick={toggleDropdown} aria-label="menu" aria-expanded="false"
                               data-target="my-nav">
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                            </button>
                    </div>

                    <div id="my-nav" className="navbar-menu">
                            <div className="navbar-start">
                                    <Link to="/" className="navbar-item" onClick={toggleDropdown}>
                                            Home
                                    </Link>
                                    {isLogged && <Link to="/weight-history" className="navbar-item" onClick={toggleDropdown}>
                                            Weight History
                                    </Link>}

                            </div>

                            <div className="navbar-end">
                                    <div className="navbar-item">
                                            <div className="buttons">
                                                    {!isLogged && <Link to="/signup" className="button is-primary" onClick={toggleDropdown}>
                                                            <strong>Sign up</strong>
                                                    </Link>}
                                                    {!isLogged && <Link to="/login" className="button has-background-grey-lighter" onClick={toggleDropdown}>
                                                            Log in
                                                    </Link>}
                                                    {isLogged && <button className="button has-background-grey-lighter" onClick={clickHandler}>
                                                            Logout
                                                    </button>}
                                            </div>
                                    </div>
                            </div>
                    </div>
            </nav>
            </div>
        );
}

