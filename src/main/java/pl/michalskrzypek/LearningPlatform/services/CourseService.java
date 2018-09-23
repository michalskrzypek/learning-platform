package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.dtos.CourseDto;
import pl.michalskrzypek.LearningPlatform.dtos.converters.CourseDtoConverter;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.repositories.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private CategoryService categoryService;
    private TagService tagService;
    private CourseDtoConverter courseDtoConverter;
    private UserService userService;

    public CourseService(CourseRepository courseRepository, CategoryService categoryService, TagService tagService,
                         UserService userService, CourseDtoConverter courseDtoConverter) {
        this.courseRepository = courseRepository;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.courseDtoConverter = courseDtoConverter;
        this.userService = userService;
    }

    public Course save(CourseDto courseDto) {
        tagService.saveNewTags(courseDto.getTags());

        Course course = courseDtoConverter.convert(courseDto);

        User instructor = userService.getCurrentUser();
        course.setInstructor(instructor);
        courseRepository.save(course);

        categoryService.addCount(course.getCategory());
        tagService.addCount(course.getTags());
        return course;
    }

    public List<Course> findAll() {
        List<Course> allCourses = new ArrayList<>();
        courseRepository.findAll().forEach(c -> allCourses.add(c));
        return allCourses;
    }

    public List<Course> findAllByCategory(String categoryName) {
        List<Course> courses = new ArrayList<>();
        Category category = categoryService.findByName(categoryName);
        courseRepository.findByCategory(category).forEach(c -> courses.add(c));
        return courses;
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).get();
    }
}
