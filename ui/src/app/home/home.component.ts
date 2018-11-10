import {Component, OnInit} from '@angular/core';
import {CourseService} from "../../shared/service/CourseService";
import {TokenStorage} from "../../shared/TokenStorage";
import {AuthService} from "../../shared/service/AuthService";
import {User} from "../../shared/model/User";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User;
  token: string = "";

  constructor(private coursesService: CourseService, private tokenStorage: TokenStorage,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(data => {
      this.user = data;
    })

    if (this.tokenStorage.getToken() != null) {
      this.token = this.tokenStorage.getToken();
    }
  }

  isAuthenticated() {
    if (this.tokenStorage.getToken() != null) {
      this.token = this.tokenStorage.getToken();
      return true;
    }
    this.tokenStorage.removeToken();
    return false;
  }
}
