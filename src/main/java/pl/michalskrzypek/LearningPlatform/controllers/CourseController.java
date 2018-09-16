package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/{category}")
    public List<Course> getCoursesByCategory(@PathVariable String category) {
        return courseService.findAllByCategory(category);
    }

    @GetMapping
    public Course getCourseById(@RequestParam Long id) {
        return courseService.findById(id);
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }
}
