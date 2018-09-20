import {Component, OnInit} from '@angular/core';
import {Course} from "../../shared/model/Course";
import {Category} from "../../shared/model/Category";
import {CategoryService} from "../../shared/service/CategoryService";

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.css']
})
export class CourseFormComponent implements OnInit {

  theCourse: Course;
  categories: Category[];
  tags: any[];

  constructor(private categoryService: CategoryService) {
    this.theCourse = new Course();
  }

  ngOnInit() {
    this.categoryService.findAll("name", false).subscribe(data => {
      this.categories = data;
    })
  }
}
