import React, {useEffect, useState} from "react";
import {getWeights} from "../Services/HttpService";
import ItemWeight from "./ItemWeight";
import ModalAddOrEdit from "./ModalAddOrEdit";
import ChartWeight from "./ChartWeight";

export default function WeightHistory() {
    const [open, setOpen] = useState(false);
    const [weights, setWeights] = useState([])
    const [refresh, setRefresh] = useState(true)
    const [weightToModal, setWeightToModal] = useState({id: -1, weight: '', date: null})

    const handleOpen = () => {
        setOpen(true)
    }
    const handleClose = () => {
        setWeightToModal({id: -1, weight: '', date: null})
        setOpen(false)
    }

    useEffect(() => {
        getWeights().then(
            resp => {
                setWeights(resp.data)
            }
        )
    }, [refresh])
    const edit = ({id, weight, date}) => {
        setWeightToModal({id: id, weight: weight, date: date});
        handleOpen()
    }
    let drawItems = weights.map((w) => <ItemWeight key={w.id} id={w.id} date={w.date.toString().split('T')[0]}
                                                   weight={w.weight} edit={edit}/>)
    let drawTable = weights.map((w) =>
        <tr key={w.id}>
            <th>{w.date.toString().split('T')[0]}</th>
            <th>{w.weight}</th>
        </tr>
    )
    const handleRefresh = () => {
        setRefresh(!refresh)
    }


    return (
        <div className="has-background-primary is-fullheight-with-navbar">
            <div className="container is-max-widescreen is-centered has-background-white-bis p-5">
                <button onClick={handleOpen} className="button is-primary mb-5">Add Weight</button>
                <div className="has-background-white box">
                    <h1 className="has-text-centered is-size-4">Your weight graph</h1>
                    {weights.length > 0 && <ChartWeight weights={weights}/>}
                </div>
                {open && <ModalAddOrEdit weight={weightToModal} handleRefresh={handleRefresh}
                                         handleOpenOrClose={handleClose}/>}
                <div className="columns">
                    <div className="column is-centered">
                        <ul>
                            {drawItems}
                        </ul>
                    </div>
                </div>
                <h1 className="has-text-centered m-3 is-size-3">Table</h1>
                <table className="table is-fullwidth is-scrollable">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Weight</th>
                    </tr>
                    </thead>
                    <tbody height="200px">
                    {drawTable}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
