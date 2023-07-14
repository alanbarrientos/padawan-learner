import React, {useEffect, useState} from "react";
import {login} from "./Services/HttpService";
import {useNavigate} from "react-router-dom";

export default function Login() {
    const navigate=useNavigate()
useEffect( () => {
    if(localStorage.key('username')){
            navigate('/')
    }
    },
)
    const [error, setError] = useState('')
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const handleSubmit = (event) => {
        event.preventDefault();
        login(username, password).then(resp => {
            if(resp){
                localStorage.setItem('username', resp.data.userName)
                localStorage.setItem('roles', resp.data.roles)
                navigate('/weighthistory')

            }
        }).catch(function (error){
            if(error.response.status === 401){
                setError('Username or Password are incorrect')
            }
        })

    };

    const handleChange = (event) => {
            if(event.target.name === 'username'){
                setUsername(event.target.value)
            }else if(event.target.name === 'password'){
                setPassword(event.target.value)
            }
    }

    return(
        <div className="hero is-primary is-fullheight-with-navbar">
            <div className="hero-body is-justify-content-center is-align-items-center">
                <div className="columns is-flex is-flex-direction-column box">
                    {error && <h2 className="help is-danger">{error}</h2>}
                    <div className="column">
                        <form onSubmit={handleSubmit} className="form">
                            <div className="field">
                                <label className="label">Username</label>
                                <input type="text" className="input" name="username" required onChange={handleChange}/>
                            </div>
                            <div className="field">
                                <label className="label">Password</label>
                                <input className="input" type="password" name="password" required
                                       onChange={handleChange}/>
                            </div>
                            <div>
                            </div>
                            <div>
                                <button className="button is-primary is-fullwidth" type="submit">Login</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}
