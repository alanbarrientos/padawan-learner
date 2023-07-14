import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {User} from "../models/user.model";
import {catchError, map, Observable, throwError} from "rxjs";
import {IsLoggedService} from "./is-logged.service";
import {getBackendUrlEndPoint} from "./utils";


const headers = new HttpHeaders().set('Content-Type', 'application/json');
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl;

  constructor(private http: HttpClient, private router: Router, private globalSrv: IsLoggedService) {
    this.baseUrl=getBackendUrlEndPoint('auth')
  }

  signup(user: User): Observable<any>{
    return this.http.post(this.baseUrl + 'signup', user, {headers, responseType: 'text' })
  }

  login(email: string, password: string){
    return this.http.post<any>(this.baseUrl + 'login',
      {email: email, password:password}, {headers, withCredentials:true})
      .pipe(catchError(err=>{
        console.error('error cought in service', err)
        return throwError(err)}),
        map(userData => {
          //localStorage is set with key username and loggedService alert
          this.globalSrv.theItem=email;
          localStorage.setItem("roles", JSON.stringify(userData.roles));
          return userData;
        })
      );
  }

  public logout(){
    return this.http.post<any>(this.baseUrl + 'logout','',{ observe: 'response', withCredentials: true });
  }

}
