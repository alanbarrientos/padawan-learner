import React, {useEffect, useState} from "react";
import landing from "./assets/landing.jpg"
import scary from "./assets/scary.png"
import goodjob from "./assets/goodjob.png"
import me from "./assets/me.jpg"
import myapp from "./assets/myapp.jpeg"
import "./assets/wickedcss.min.css"
import {getLastWeights, getFirstWeights} from "./Services/HttpService";
import {isLogged} from "./Services/LoggedService";

export default function Landing() {
    const [firstWeight, setFirstWeight] = useState([0])
    const [lastWeight, setLastWeight] = useState([0])
    const [logged, setLogged] = useState(false)

    useEffect(() => {
        if (isLogged()) {
            setLogged(true)
            getFirstWeights().then(response => {
                console.log(response.data)
                setFirstWeight(response.data)
            })
            getLastWeights().then(response => {
                console.log(response.data)
                setLastWeight(response.data)
            })
        }
    }, [0])

    const stateOfWeight = () => {
        if (firstWeight[0] < lastWeight[0]) {
            return "gain"
        } else if (firstWeight[0] > lastWeight[0]) {
            return "loss"
        }
        return "equal"
    }
    const weightGainOrLoss = () => {
        if (firstWeight[0] > lastWeight[0]) {
            let weightToRound = firstWeight[0] - lastWeight[0]
            return Math.round(weightToRound * 100)/100
        }
        let weightToRound = lastWeight[0] - firstWeight[0]
        return Math.round(weightToRound * 100)/100
    }


    return (
        <div>
            <div className="columns">
                <div className="column hero has-background-primary is-fullheight-with-navbar">
                    <div className="hero-body has-text-centered">
                        <div className="container is-fluid">
                            <div>
                                <div className="mt-0">
                                    <h1 className="has-text-white is-text is-size-2-mobile is-size-1 is-family-monospace has-text-weight-bold">
                                        Dietapp
                                    </h1>
                                </div>
                            </div>
                            <h2 className="is-text is-size-2-mobile is-size-2">
                                Where you can track your progress
                            </h2>
                            <div className="mt-6">
                                {logged ? <div className="columns box m-4">
                                        <div className="column is-half box has-background-grey-light">
                                            <h2 className="subtitle is-size-4 ">
                                                You started with: {firstWeight} kg<br/>
                                                Now you have: {lastWeight} kg
                                            </h2>
                                        </div>
                                        <div className="column is-half">
                                            {stateOfWeight() == "gain" &&
                                                <div className="has-text-centered">
                                                    <img src={scary}
                                                         className="rotateIn image is-128x128 is-inline-block"/>
                                                    <div className="box has-background-danger">
                                                        <h1 className="is-size-4 has-background-grey-lighter">You have
                                                            gain {weightGainOrLoss()} kg
                                                        </h1>
                                                    </div>
                                                    <p></p>
                                                </div>
                                            }
                                            {stateOfWeight() == "loss" &&
                                                <div className="has-text-centered">
                                                    <img src={goodjob}
                                                         className="slideDown image is-128x128 is-inline-block"/><br/>
                                                    <div className="box has-background-success">
                                                        <h1 className="is-size-4 has-background-grey-lighter">You have
                                                            lost {weightGainOrLoss()} kg
                                                        </h1>
                                                    </div>
                                                </div>
                                            }
                                            {stateOfWeight() == "equal" &&
                                                <div className="has-text-centered">
                                                    <img src={me}
                                                         className="zoomer image is-128x128 is-inline-block"/><br/>
                                                    <div className="box has-background-grey">
                                                        <h1 className="is-size-4 has-background-grey-lighter">You have the
                                                            same
                                                            weigth
                                                        </h1>
                                                    </div>
                                                </div>
                                            }
                                            <br/>
                                        </div>
                                    </div>
                                    :
                                    <div className="box has-background-white-bis">
                                        <img src={myapp} className="is-bordered"/>
                                    </div>
                                }
                            </div>
                        </div>
                    </div>
                </div>
                <div className="column has-background-primary hero is-fullheight-with-navbar is-hidden-mobile">
                    <div className="hero-body card-image">
                        <img src={landing} className="hero-body "/>
                    </div>
                </div>
            </div>
        </div>
    )
}
