package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.michalskrzypek.LearningPlatform.common.Roles;
import pl.michalskrzypek.LearningPlatform.dtos.CourseDto;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.services.CourseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/{category}")
    public List<Course> getCoursesByCategory(@PathVariable String category) {
        return courseService.findAllByCategory(category);
    }

    @GetMapping(params = {"id"})
    public Course getCourseById(@RequestParam Long id) {
        return courseService.findById(id);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('" + Roles.ROLE_INSTRUCTOR + ", "+ Roles.ROLE_ADMIN + "')")
    @ResponseStatus(HttpStatus.CREATED)
    public Course addNewCourse(@Valid @RequestBody CourseDto courseDto){
        return courseService.save(courseDto);
    }
}
