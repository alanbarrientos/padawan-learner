import { Injectable } from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpRequest, HttpStatusCode} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Router} from "@angular/router";
import {IsLoggedService} from "./is-logged.service";
import {Toast, ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService {
  constructor(public router: Router, private isLogged:IsLoggedService, private toastr:ToastrService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError(error => {
        this.handleError(error)
        console.error("error is intercept", error);
        return throwError(error.message);
      })
    );
  }

  private handleError(httpError: HttpErrorResponse) {
    let message:string = '';
    console.log(httpError.status);
    // if(httpError.status==200){
    //   return
    // }
    if(httpError.status == HttpStatusCode.Unauthorized){
      // this.toastr.error('Error 401 no authorized','Error');
      console.log('Estoy en un error 401');
      this.isLogged.theItem=null;
      localStorage.clear();
      this.router.navigateByUrl('/login');

    } else if (httpError.error instanceof ProgressEvent) {
      this.toastr.error('Network error','Error');
      console.log('in progress event');
      message = "Network error";
    }

      // if (httpError.error instanceof ErrorEvent) {
      //   message = httpError.error.message;
      //   // A client-side or network error occurred. Handle it accordingly.
      //   console.error('An error occurred:', httpError.error.message);
    // }
    else {
      // message = JSON.parse(httpError.error).message;
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      // alert('some error code:' + httpError.status)
     this.toastr.error('some error code:'+httpError.status,'Error');
      console.error(
        `Backend returned code ${httpError.status}, ` +
        `body was: ${httpError.error}`);
    }
    // Return an observable with a user-facing error message.
    // return throwError(
    //   'Something bad happened; please try again later. Error Message- ' + message);
  }
}
