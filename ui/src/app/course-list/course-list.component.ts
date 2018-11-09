import {Component, OnInit} from '@angular/core';
import {Course} from "../../shared/model/Course";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../../shared/service/CourseService";
import {UserService} from "../../shared/service/UserService";

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  category: string;
  courses: Course[];

  constructor(private route: ActivatedRoute, private router: Router, private courseService: CourseService, private userService: UserService) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (params['category'] != null) {
        this.category = params['category'];
        this.courseService.findAllByCategory(this.category).subscribe(data => {
          this.courses = data;
        });
      } else {
        this.courseService.findAll().subscribe(data => {
          this.courses = data;
        })
      }
    });
  }

  assign(course: Course) {
    this.userService.assignCourse(course.id).subscribe(data => {
      this.router.navigate(['/my-courses']);
    });
  }
}
