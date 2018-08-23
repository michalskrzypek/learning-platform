import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../../shared/service/AuthService";
import {TokenStorage} from "../../shared/authentication/TokenStorage";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  credentials = {username: '', password: ''};

  constructor(private authService: AuthService, private http: HttpClient, private router: Router, private token: TokenStorage) {
  }

  login(): void {
    this.authService.login(this.credentials.username, this.credentials.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        console.log(data.token);
        this.router.navigate(['home']);
      }
    );
  }
}
