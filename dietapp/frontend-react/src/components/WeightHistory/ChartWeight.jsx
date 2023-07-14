import React from "react";
import ReactEChart from "echarts-for-react"
export default function ChartWeight(weights){
    const eChartOption={
        xAxis:{
            type: 'category',
            splitLine:{
                show:true
            },
            data: weights.weights.slice(0).reverse().map((w) => ({
                value:w.date.toString().split('T')[0]
            }))
        },
        yAxis:{
            scale:true,
            type: 'value',
            splitLine: {
                show: true
            },
        },
        series:{
            data:weights.weights.slice(0).reverse().map((w) => ({
                value:w.weight
            })),
            type:"line"
        }
    };
    return (
        <div>
            <ReactEChart option={eChartOption}/>
        </div>

    )

}
