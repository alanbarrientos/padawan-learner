import React from "react";
import {useState} from "react";
import {signup} from "./Services/HttpService";
import User from "./Models/User";
import {useNavigate} from "react-router-dom";

export default function Signup() {

    const [username, setUsername] = useState('')
    const [maleOrFemale, setMaleOrFemale] = useState(true)
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState({})

    const navigate = useNavigate();

    const validate = () => {
        const errors = {}
        //---------------------------------------------
        // username validation
        if (!username) {
            errors.username = 'username is required'
        } else if (username.length <= 3) {
            errors.username = 'Username must be longer than 2 characters'
        }

        //---------------------------------------------
        //email validation
        if (!email) {
            errors.email = 'email is required'
        } else if (!email.match('[a-z0-9]+@[a-z]+\.[a-z]{2,3}')) {
            errors.email = 'Is not a valid email'
        }
        //---------------------------------------------
        //password validation
        if (!password) {
            errors.password = 'password is required'
        }

        setError(errors)
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        validate()
        if (error=={}) {
            const user = new User(username, email, password, maleOrFemale)
            signup(user).then(resp => {
                if (resp) {
                    console.log(resp)
                    navigate('/login')
                }
            }).catch(function (error) {
                if (error.response.data == 'Username is already taken') {
                    setError({username: error.response.data})
                }
                if (error.response.data == 'This email is already registered') {
                    setError({email: error.response.data})
                }
            })
        }
    }

    const handleChange = (event) => {
        if (event.target.name === 'username') {
            setUsername(event.target.value)
        } else if (event.target.name === 'email') {
            setEmail(event.target.value)
        } else if (event.target.name === 'maleOrFemale') {
            setMaleOrFemale(event.target.value)
        } else if (event.target.name === 'password') {
            setPassword(event.target.value)
        }
    }

    return (
        <div className="hero is-primary is-fullheight-with-navbar">
            <div className="hero-body is-justify-content-center is-align-items-center">
                <div className="columns is-flex is-flex-direction-column box">
                    <div className="column">
                        <form onSubmit={handleSubmit} className="form">
                            <div className="field">
                                <div>
                                    <label className="label">Username</label>
                                    <input className="input" type="text" name="username" onChange={handleChange}/>
                                </div>
                                {error.username && <p className="help is-danger">{error.username}</p>}
                            </div>
                            <div className="field">
                                <div>
                                    <label className="label">Email</label>
                                    <input className="input" type="text" name="email" onChange={handleChange}/>
                                </div>
                                {error.email && <p className="help is-danger">{error.email}</p>}
                            </div>
                            <div className="field">
                                <label className="label">Gender</label>
                                <div className="select  mb-1">
                                    <select className="dropdown" onChange={(e) => {
                                        if (e = 0) {
                                            setMaleOrFemale(true)
                                        } else {
                                            setMaleOrFemale(false)
                                        }
                                    }}>
                                        <option value={0}>Male</option>
                                        <option value={1}>Female</option>
                                    </select>
                                    {/*<label className="label">Male or Female</label>*/}
                                    {/*<input className="input" type="text" name="maleOrFemale" required onChange={handleChange} />*/}
                                </div>
                            </div>
                            <div className="field">
                                <div>
                                    <label className="label">Password</label>
                                    <input className="input" type="password" name="password" onChange={handleChange}/>
                                </div>
                                {error.password && <p className="help is-danger">{error.password}</p>}
                            </div>
                            <div className="field">
                                <div>
                                    <button className="button is-primary is-fullwidth" type="submit">Signup</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}
