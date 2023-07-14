import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpStatusCode} from "@angular/common/http";
import {Router} from "@angular/router";
import {User} from "../models/user.model";
import {catchError, Observable, throwError} from "rxjs";
import {Weight} from "../models/weight.model";
import {IsLoggedService} from "./is-logged.service";
import {getBackendUrlEndPoint} from "./utils";

const headers = new HttpHeaders().set('Content-Type', 'text');
@Injectable({
  providedIn: 'root'
})
export class UserService {
  // private baseUrl = window.origin + '/user/';
    private baseUrl ;


  constructor(private http: HttpClient, private router: Router, private globalSrv: IsLoggedService ) {
    this.baseUrl=getBackendUrlEndPoint('user')
  }

  getByUserRole():  Observable<any>{
    return this.http.get(this.baseUrl+'displayuser', {responseType:'text', withCredentials: true})
  }

  getWeightHistory():  Observable<Weight[]>{
    return this.http.get<Weight[]>(this.baseUrl+'getweighthistory', {responseType:'json', withCredentials: true})
  }
  addWeight(weight:Weight):Observable<any>{
    return this.http.post(this.baseUrl+'postweight', weight, {withCredentials: true})
  }
  updateWeight(weight:Weight):Observable<any>{
    return this.http.post(this.baseUrl + 'postweight/'+weight.id, weight, {withCredentials: true})
  }
  deleteWeight(id: number):Observable<any>{
    return this.http.delete(this.baseUrl + id, {withCredentials:true})
  }

  getLastWeights(){
    return this.http.get<[]>(this.baseUrl + 'getlastweight', {withCredentials:true})
  }

  getFirstWeights(){
    return this.http.get<[]>(this.baseUrl + 'getfirstweight', {withCredentials:true})
  }


}
