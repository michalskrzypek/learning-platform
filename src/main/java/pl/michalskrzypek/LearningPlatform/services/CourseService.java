package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.dtos.CourseDto;
import pl.michalskrzypek.LearningPlatform.dtos.converters.CourseDtoConverter;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.entities.Tag;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.enums.MailType;
import pl.michalskrzypek.LearningPlatform.exceptions.CourseNotFoundException;
import pl.michalskrzypek.LearningPlatform.repositories.CourseRepository;
import pl.michalskrzypek.LearningPlatform.services.mails.MailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private CategoryService categoryService;
    private TagService tagService;
    private CourseDtoConverter courseDtoConverter;
    private UserService userService;
    private MailService mailService;


    public CourseService(CourseRepository courseRepository, CategoryService categoryService, TagService tagService,
                         UserService userService, CourseDtoConverter courseDtoConverter, MailService mailService) {
        this.courseRepository = courseRepository;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.courseDtoConverter = courseDtoConverter;
        this.userService = userService;
        this.mailService = mailService;
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

    public Course save(CourseDto courseDto) {
        saveNewTags(courseDto);

        Course course = courseDtoConverter.convert(courseDto);
        User instructor = userService.getCurrentUser();
        course.setInstructor(instructor);
        Course savedCourse = courseRepository.save(course);

        increaseCorrespondingCounts(savedCourse);
        notifyInstructorAboutCreatedCourse(instructor);

        return course;
    }

    private void saveNewTags(CourseDto courseDto){
        Optional.ofNullable(courseDto.getTags())
                .ifPresent(tags -> tagService.saveNewTags(courseDto.getTags()));
    }

    private void increaseCorrespondingCounts(Course course) {
        categoryService.increaseCount(course.getCategory());
        tagService.increaseCount(course.getTags());
    }

    private void notifyInstructorAboutCreatedCourse(User instructor){
        mailService.notifyUser(instructor, MailType.NEW_COURSE);
    }

    public void deleteCourse(Long id) {
        Course courseToDelete = findById(id);
        decreaseCorrespondingCounts(courseToDelete);
        courseRepository.delete(courseToDelete);
    }

    public Course findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        return course;
    }

    private void decreaseCorrespondingCounts(Course course) {
        categoryService.decreaseCount(course.getCategory());
        tagService.decreaseCount(course.getTags());
    }
}
