import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-role-user',
  templateUrl: './role-user.component.html',
  styleUrls: ['./role-user.component.css']
})
export class RoleUserComponent implements OnInit {
  content: string | undefined;
  constructor(private userService: UserService) { }
  ngOnInit(): void {
    this.userService.getByUserRole().subscribe(
      data => {
        this.content = data;
      },
      error => {
        this.content = error;
      }
    );
  }

}
