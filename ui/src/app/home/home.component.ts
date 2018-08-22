import {Component, OnInit} from '@angular/core';
import {CourseService} from "../../shared/service/CourseService";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../../shared/service/AuthService";
import {TokenStorage} from "../../shared/authentication/TokenStorage";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  email: string = "To jest adres email.";

  courses = {}

  constructor(private coursesService: CourseService, private http: HttpClient, private authService: AuthService, private tokenStorage: TokenStorage) {
    coursesService.findAll().subscribe(data => {
      this.courses = data;
    })
  }

  ngOnInit() {
  }

  isAuthenticated() {
    if (this.tokenStorage.getToken() != null) {
      return true;
    }
    return false;
  }
}
