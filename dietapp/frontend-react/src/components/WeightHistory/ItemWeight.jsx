import React from "react";

export default function ItemWeight(props){
    const handleClick = () => {
        props.edit({id:props.id, weight:props.weight, date:props.date})
    }
    return(
        <div className="box" onClick={handleClick}>
            <h2>{props.date}</h2>
            <h1 className="subtitle">{props.weight} kg.</h1>
        </div>
    )
}
