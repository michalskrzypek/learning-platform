import { Component, OnInit } from '@angular/core';
import {User} from "../../shared/model/User";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  newUser = new User();

  constructor() { }

  ngOnInit() {
  }

  signup() {
    console.log(this.newUser)
  }
}
