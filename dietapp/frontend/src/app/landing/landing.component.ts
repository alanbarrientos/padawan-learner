import { Component, OnInit } from '@angular/core';
import {IsLoggedService} from "../services/is-logged.service";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  firstWeight = [0]
  lastWeight = [0]
  logged = false

  constructor( private isloggedservice:IsLoggedService, private http:UserService) { }

  ngOnInit(): void {
    if(this.isloggedservice.theItem){
      console.log(this.isloggedservice.theItem)
      this.logged=true
      this.http.getFirstWeights().subscribe(response => {
        this.firstWeight=response
      })
      this.http.getLastWeights().subscribe(response => {
        this.lastWeight=response
      })
    }
  }

  stateOfWeight(){
    if (this.firstWeight[0] < this.lastWeight[0]) {
      return 'gain'
    } else if (this.firstWeight[0] > this.lastWeight[0]) {
      return 'loss'
    }
    return 'equal'
  }

  weightGainOrLoss(){
    if (this.firstWeight[0] > this.lastWeight[0]) {
      return this.firstWeight[0] - this.lastWeight[0]
    }
    return this.lastWeight[0] - this.firstWeight[0]
  }

}
