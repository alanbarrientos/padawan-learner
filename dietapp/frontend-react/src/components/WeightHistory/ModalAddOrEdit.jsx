import React, {useEffect, useState} from 'react';
import TextField from '@mui/material/TextField';
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import {addWeight,updateWeight, deleteWeight} from "../Services/HttpService";
import Weight from "../Models/Weight";
import 'bulma/css/bulma.min.css';


const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

export default function ModalAddOrEdit(props) {
    let [date, setDate] = useState(props.weight.date);
    const [weight, setWeight] = useState(props.weight.weight)
    const [id, setId] = useState(props.weight.id)

    useEffect(() => {
        if(id==-1){
            setDate(new Date)
        }
    },[id])

    const closeModal = () => {
        setWeight(null)
        setDate(null)
        setId(null)
        props.handleOpenOrClose()
    }

    const onClickAddWeight = () => {
        let datetosend = date == null ? new Date : date
        let data=new Weight(-1,datetosend ,weight)
        addWeight(data).catch((error) => console.log(error)).then(response => {
            if(response instanceof Error){
                console.log(response)
            }else {
                console.log(response)
                setWeight(null)
                setDate(null)
                setId(null)
                closeModal()
                props.handleRefresh()
            }
        })
    }

    const onClickUpdateWeight = () => {
        let data = new Weight(id,date,weight)
        updateWeight(data).then(response => {
                console.log(response)
                setWeight(null)
                setDate(null)
                setId(null)
                closeModal()
                props.handleRefresh()
        }).catch(err => console.log(err))

    }

    const onClickDeleteWeight = () => {
        deleteWeight(id).then(response => {
            if(response instanceof Error){
                console.log(response)
            }else {
                console.log(response)
                setWeight(null)
                setDate(null)
                setId(null)
                props.handleRefresh()
                closeModal()
            }
        })

    }


    return(
        <div>
            <div className="modal is-active">
                <div className="modal-background" onClick={closeModal}></div>
                <div className="modal-card">
                    <header className="modal-card-head">
                        <p className="modal-card-title">{props.weight.id == -1 ? <span>Add Weight</span> : <span>Edit Weigth</span>}</p>
                        <button className="delete" aria-label="close" onClick={closeModal}></button>
                    </header>
                    <section className="modal-card-body">
                        <form>
                            <div className="columns">
                                <div className="column is-half">
                            <LocalizationProvider dateAdapter={AdapterDayjs} >
                                <DatePicker className="mb-4"
                                    label="Date"
                                    value={date}
                                    inputFormat="DD/MM/YYYY"
                                    onChange={(newValue) => {
                                        setDate(newValue);
                                    }}
                                    renderInput={(params) => <TextField {...params} />}
                                    disableFuture={true}
                                />
                            </LocalizationProvider>
                                    <label className="label">Weight</label>
                            <input placeholder="Weight" id="weight" label="weight" className="input" value={weight} onChange={(e) => setWeight(e.target.value)} />
                            </div>
                            </div>
                        </form>
                    </section>
                    <footer className="modal-card-foot">
                        {props.weight.id == -1 &&
                            <button className="button is-success" onClick={onClickAddWeight}>
                                Add
                            </button>
                        }
                        {props.weight.id != -1 &&
                            <button className="button is-success" onClick={onClickUpdateWeight}>
                                Edit
                            </button>
                        }
                        {props.weight.id !=-1 &&
                            <button className="button is-danger" onClick={onClickDeleteWeight}>
                                Delete
                            </button>
                        }
                        <button className="button" onClick={closeModal}>
                            Cancel
                        </button>
                    </footer>
                </div>
            </div>
        </div>
    )
}
