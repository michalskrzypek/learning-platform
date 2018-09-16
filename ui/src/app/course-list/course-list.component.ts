import {Component, OnInit} from '@angular/core';
import {Course} from "../../shared/model/Course";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../../shared/service/CourseService";

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  category: string;
  courses: Course[];

  constructor(private route: ActivatedRoute, private router: Router, private courseService: CourseService) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.category = params['category'];
    });

    this.courseService.findAllByCategory(this.category).subscribe(data => {
      this.courses = data;
    })
  }

}
