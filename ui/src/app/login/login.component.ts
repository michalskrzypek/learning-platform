import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../shared/service/AuthService";
import {TokenStorage} from "../../shared/TokenStorage";
import {LoginUser} from "../../shared/model/LoginUser";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser = new LoginUser();
  logoutParam: boolean;

  constructor(private authService: AuthService, private http: HttpClient, private router: Router, private token: TokenStorage,
              private toastr: ToastrService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    if(this.route.snapshot.queryParams["logout"] != null){
      this.logoutParam = true;
    }
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
