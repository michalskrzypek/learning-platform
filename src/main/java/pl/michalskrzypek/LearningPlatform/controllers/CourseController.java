package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.michalskrzypek.LearningPlatform.annotations.IsInstructor;
import pl.michalskrzypek.LearningPlatform.common.Roles;
import pl.michalskrzypek.LearningPlatform.dtos.CourseDto;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.services.CourseService;
import pl.michalskrzypek.LearningPlatform.services.TagService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;
    private TagService tagService;

    public CourseController(CourseService courseService, TagService tagService) {
        this.courseService = courseService;
        this.tagService = tagService;
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
    @IsInstructor
    @ResponseStatus(HttpStatus.CREATED)
    public Course addNewCourse(@Valid @RequestBody CourseDto courseDto) {
        tagService.saveNewTags(courseDto.getTags());
        Course newCourse = courseService.save(courseDto);
        courseService.increaseCorrespondingCounts(newCourse);
        return newCourse;
    }
}
