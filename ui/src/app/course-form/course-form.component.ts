import {Component, OnInit} from '@angular/core';
import {Course} from "../../shared/model/Course";
import {Category} from "../../shared/model/Category";
import {CategoryService} from "../../shared/service/CategoryService";
import {CourseService} from "../../shared/service/CourseService";

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.css']
})
export class CourseFormComponent implements OnInit {

  theCourse: Course;
  categories: Category[];

  constructor(private courseService: CourseService, private categoryService: CategoryService) {
    this.theCourse = new Course();
  }

  ngOnInit() {
    this.categoryService.findAll("name", false).subscribe(data => {
      this.categories = data;
    })
  }

  save() : void{
    this.courseService.save(this.theCourse).subscribe(data => {
      console.log(data);
    });
  }
}
