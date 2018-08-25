import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../../shared/service/AuthService";
import {TokenStorage} from "../../shared/authentication/TokenStorage";
import {LoginUser} from "../../shared/model/LoginUser";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginUser = new LoginUser();

  constructor(private authService: AuthService, private http: HttpClient, private router: Router, private token: TokenStorage) {
  }

  login(): void {
    this.authService.login(this.loginUser.username, this.loginUser.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        console.log(data.token);
        this.router.navigate(['home']);
      }
    );
  }
}
