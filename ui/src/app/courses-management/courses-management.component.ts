import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Pager} from "../../shared/model/Pager";
import {Course} from "../../shared/model/Course";
import {CourseService} from "../../shared/service/CourseService";
import {UserService} from "../../shared/service/UserService";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-courses-management',
  templateUrl: './courses-management.component.html',
  styleUrls: ['./courses-management.component.css']
})
export class CoursesManagementComponent implements OnInit {

  courses: Course[];

  pager = new Pager();

  constructor(private route: ActivatedRoute, private router: Router, private courseService: CourseService, private userService: UserService, private toastr: ToastrService) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.pager.sortBy = "id";
  }

  ngOnInit() {
    this.courseService.findAll(this.pager).subscribe(data => {
      this.courses = data['content'];
      this.setPager(data);
    });
  }

  setPager(data: any) {
    this.pager.totalElements = data['totalElements'];
    this.pager.totalPages = data['totalPages'];
    this.pager.first = data['first'];
    this.pager.last = data['last'];
  }

  changePage(page: number) {
    this.pager.number = page;

    this.courseService.findAll(this.pager).subscribe(data => {
      this.courses = data['content'];
      this.setPager(data);
    });
  };

  counter(i: number) {
    return new Array(i);
  }

  delete(courseId: number) {
    this.courseService.delete(courseId).subscribe(data => {
      this.toastr.show("Course deleted!")
      this.changePage(this.pager.number);
    });
  }
}
