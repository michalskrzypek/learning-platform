import { Component, OnInit } from '@angular/core';
import {User} from "../../shared/model/User";
import {AuthService} from "../../shared/service/AuthService";
import {Router} from "@angular/router";
import {GlobalVariable} from "../../shared/util/Globals";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  newUser = new User();

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.newUser.role = GlobalVariable.BASE_ROLE;
  }

  signup() {
    this.authService.signup(this.newUser).subscribe(data => {
      this.router.navigate(['login'])
    })
  }
}
