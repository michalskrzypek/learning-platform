import {Component, OnInit} from '@angular/core';
import {CourseService} from "../../shared/service/CourseService";
import {TokenStorage} from "../../shared/TokenStorage";
import {User} from "../../shared/model/User";
import {UserService} from "../../shared/service/UserService";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User;
  token: string = "";

  constructor(private coursesService: CourseService, private tokenStorage: TokenStorage,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(data => {
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
