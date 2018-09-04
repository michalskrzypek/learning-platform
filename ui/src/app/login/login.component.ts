import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../../shared/service/AuthService";
import {TokenStorage} from "../../shared/TokenStorage";
import {LoginUser} from "../../shared/model/LoginUser";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginUser = new LoginUser();

  constructor(private authService: AuthService, private http: HttpClient, private router: Router, private token: TokenStorage,
              private toastr: ToastrService) {
  }

  login(): void {
    this.authService.login(this.loginUser).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.toastr.success("You have been logged in!")
        this.router.navigate(['home']);
      }
    );
  }
}
