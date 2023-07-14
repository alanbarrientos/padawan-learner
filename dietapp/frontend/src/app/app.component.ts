import {Component, ElementRef, OnChanges, OnInit, SimpleChanges,} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "./services/auth.service";
import {IsLoggedService} from "./services/is-logged.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  error=false;
  login=false;
  constructor(private router:Router, private http:AuthService, private globalSrv: IsLoggedService) {
  }

  ngOnInit() {
    this.globalSrv.itemValue.subscribe((nextValue) => {
           if(nextValue!=null){
             this.login=true;
           }else{
             this.login=false;
           }
    })
    if(localStorage.getItem('username')!=null){
      this.login=true;
    }
  }

  logout(){
    console.log('Se supone que aprete logout')
    localStorage.clear();
    this.login=false;
    this.router.navigate(['/login']);
    this.http.logout().subscribe(
      data=>{
        },
      error=>{
        console.log(error);
      })

  }
  toggleDropdown() {
    let myNav = document.querySelector("#my-nav")
    let burger = document.querySelector("#burger")
    myNav!.classList.toggle("is-active")
    burger!.classList.toggle("is-active")
  }
}
