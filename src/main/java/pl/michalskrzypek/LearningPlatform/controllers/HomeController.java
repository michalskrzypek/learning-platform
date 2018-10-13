package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.services.CourseService;
import pl.michalskrzypek.LearningPlatform.services.UserService;

import java.util.List;

@RestController
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my-courses")
    public List<Course> getMyCourses() {
        return userService.findAllCourses();
    }
}
