import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {UserService} from "../services/user.service";
import {Weight} from "../models/weight.model";
import {Subscription} from "rxjs";
import {EChartsOption, ECharts} from "echarts";

@Component({
  selector: 'app-chart-weight',
  templateUrl: './chart-weight.component.html',
  styleUrls: ['./chart-weight.component.css']
})
export class ChartWeightComponent implements  OnChanges{
  @Input() weights!: Weight[];
  localWeight!:Weight[];
  _chartOption!: EChartsOption;
  subscription!:Subscription;
  constructor(private http:UserService) { }

  ngOnChanges(changes: SimpleChanges) {
    this._initBasicLineEchart()
  }

  _initBasicLineEchart(){
  this._chartOption={
    tooltip:{
      show:true
    },
    background: 'transparent',
      xAxis: {
        type: 'category',
        inverse:true,
        splitLine:{
          show: true
        },
        data: this.weights.map(w =>({
          value:w.date.toString().split('T')[0]
        }))
      },
      yAxis: {
      scale:true,
        type: 'value',
        splitLine: {
      show: true
    },
      },
      series: [
        {
          data: this.weights.map(w=>({
            value:w.weight
          })),
          type: 'line'
        }
      ]
  };
  }

}
