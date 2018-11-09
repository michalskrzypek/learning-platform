import {Component, OnInit} from '@angular/core';
import {Course} from "../../shared/model/Course";
import {Category} from "../../shared/model/Category";
import {CategoryService} from "../../shared/service/CategoryService";
import {CourseService} from "../../shared/service/CourseService";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.css']
})
export class CourseFormComponent implements OnInit {

  theCourse: Course;
  categories: Category[];

  constructor(private courseService: CourseService, private categoryService: CategoryService, private router: Router,
              private toastr: ToastrService) {
    this.theCourse = new Course();
  }

  ngOnInit() {
    this.categoryService.findAll("name", false).subscribe(data => {
      this.categories = data;
    });
  }

  save(): void {
    this.courseService.save(this.theCourse).subscribe(data => {
      this.router.navigate(["courses/all"]);
      this.toastr.success("Title: \"" + data.title + "\"", "New course has been added!")
    });
  }
}
