package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.exceptions.CourseNotFoundException;
import pl.michalskrzypek.LearningPlatform.services.CourseService;
import pl.michalskrzypek.LearningPlatform.services.UserService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    private UserService userService;
    private CourseService courseService;

    public HomeController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/my-courses")
    public List<Course> getMyCourses() {
        return userService.findAllCourses();
    }

    @PostMapping("/my-courses/add/{id}")
    public Course assignCourse(@PathVariable("id") Long courseId) {
        Course course = courseService.findById(courseId);
        return userService.assignCourse(course);
    }
}
