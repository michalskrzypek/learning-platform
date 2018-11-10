import {Component, OnInit} from '@angular/core';
import {Course} from "../../shared/model/Course";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../../shared/service/CourseService";
import {UserService} from "../../shared/service/UserService";
import {Pager} from "../../shared/model/Pager";

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  category: string;
  courses: Course[];

  pager = new Pager();
  totalPages = new Array(10);

  constructor(private route: ActivatedRoute, private router: Router, private courseService: CourseService, private userService: UserService) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (params['category'] != null) {
        this.category = params['category'];
        this.courseService.findAllByCategory(this.category, this.pager).subscribe(data => {
          this.courses = data['content'];
          this.setPager(data);
        });
      } else {
        this.courseService.findAll(this.pager).subscribe(data => {
          this.courses = data['content'];
          this.setPager(data);
        })
      }
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

    if (this.category != null) {
      this.courseService.findAllByCategory(this.category, this.pager).subscribe(data => {
        this.courses = data['content'];
        this.setPager(data);
      });
    } else {
      this.courseService.findAll(this.pager).subscribe(data => {
        this.courses = data['content'];
        this.setPager(data);
      })
    }
  };

  counter(i: number) {
    return new Array(i);
  }

  assign(course
           :
           Course
  ) {
    this.userService.assignCourse(course.id).subscribe(data => {
      this.router.navigate(['/my-courses']);
    });
  }
}
