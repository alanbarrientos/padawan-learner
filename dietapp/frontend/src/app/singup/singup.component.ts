import { Component, OnInit } from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import { User } from '../models/user.model';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.css']
})
export class SingupComponent implements OnInit {
  seePassword = false;
  roles = [
    {name:'User', id:1, selected: true},
    {name:'Admin', id:2, selected: false},
  ]
  signupForm!:FormGroup;
  user = new User('', '', '', false);
  isRegistered = false;
  submitted = false;
  errorMessage = '';
  constructor(private authHttp: AuthService , private router:Router) { }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(25)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(4)]),
      gender: new FormControl(0, [Validators.required]),
    });
  }
  createRoles(rolesList: any[]): FormArray{
    const arr = rolesList.map(role => {
      return new FormControl(role.selected)
    });
    return new FormArray(arr);
  }

  onSubmit() {
    if (this.signupForm.valid) {
      this.submitted = true;
      this.user.userName = this.signupForm.value.name;
      this.user.email = this.signupForm.value.email;
      this.user.password = this.signupForm.value.password;
      if (this.signupForm.value.gender = 1) {
        this.user.isMale = true;
      }
      console.log(this.user);
      this.registerUser();
    }
  }


  registerUser(){
    this.authHttp.signup(this.user)
      .subscribe(user=> {
        console.log(user);
        this.isRegistered = true;
        this.router.navigate(['login'])
      }, error=> {
        console.log(error);
        this.errorMessage = error;
        this.isRegistered = false;
      });
  }
}
