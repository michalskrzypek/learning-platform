package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.michalskrzypek.LearningPlatform.dtos.UserDto;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.services.CourseService;
import pl.michalskrzypek.LearningPlatform.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private CourseService courseService;

    public UserController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping
    public List getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/current")
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerNewUser(@Valid @RequestBody UserDto newUser){
        return userService.saveUser(newUser);
    }

    @GetMapping("/courses")
    public List<Course> getCoursesOfTheCurrentUser() {
        return userService.getCoursesOfTheCurrentUser();
    }

    @PutMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course assignCourseToTheCurrentUser(@PathVariable("id") Long courseId) {
        Course courseToAssign = courseService.getById(courseId);
        return userService.assignCourseToTheCurrentUser(courseToAssign);
    }
}
