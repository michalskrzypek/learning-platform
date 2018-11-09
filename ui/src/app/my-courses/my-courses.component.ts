import {Component, OnInit} from '@angular/core';
import {Course} from "../../shared/model/Course";
import {UserService} from "../../shared/service/UserService";

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css']
})
export class MyCoursesComponent implements OnInit {

  courses: Course[];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.findAllCourses().subscribe(data => {
      this.courses = data;
    });
  }

}
