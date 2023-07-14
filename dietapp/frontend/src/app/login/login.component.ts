import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import { IsLoggedService } from '../services/is-logged.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted = false;
  errorMessage = '';
  isLoggedin = false;
  isLoginFailed = false;
  constructor(private authHttp:AuthService, private router: Router , private islogged:IsLoggedService) { }

  ngOnInit(): void {
    if(this.islogged.theItem){
      this.router.navigate(['/'])
    }
    this.loginForm = new FormGroup({
      name: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      });
  }

  onSubmit(){
    if(this.loginForm.valid){
      this.submitted = true;
      this.authHttp.login(this.loginForm.value.name, this.loginForm.value.password).subscribe(
        data=>{
          this.isLoggedin = true
          this.router.navigate(['/weight-history']);
        },
        error=>{
          console.log(error);
          this.errorMessage = error;
          this.isLoggedin = false;
          this.isLoginFailed = true;
        }
      )
    }
  }

}
