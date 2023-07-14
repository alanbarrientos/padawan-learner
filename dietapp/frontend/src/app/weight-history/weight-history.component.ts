import {Component, ElementRef, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";
import {Weight} from "../models/weight.model";
import {last, Observable} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-weight-history',
  templateUrl: './weight-history.component.html',
  styleUrls: ['./weight-history.component.css']
})
export class WeightHistoryComponent implements OnInit  {
  modalAdd=false;
  modalOpen=false;
  weightForSent:Weight|null=null;
  weights: Weight[]= [];
  dateWeights!:Date[];
  constructor(private http: UserService, private el:ElementRef, private router:Router) {
    this.http.getWeightHistory()
      .subscribe(weight => this.weights = weight);
  }

  ngOnInit(): void {
  }

  obtainWeights(){
    this.http.getWeightHistory()
      .subscribe(weight => this.weights = weight);
  }

  AddOpenOrClose(){
    this.weightForSent=null;
    this.modalAdd=!this.modalAdd;
  }

  openWeight(weight:Weight){
    this.weightForSent=weight;
    this.modalAdd=!this.modalAdd;
  }
  closeWeight(){
    this.modalOpen=!this.modalOpen;
  }
  refresh(){
    this.http.getWeightHistory()
      .subscribe(weight => this.weights = weight);
  }


}
