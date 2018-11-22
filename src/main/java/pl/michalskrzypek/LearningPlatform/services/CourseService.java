package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
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

import java.util.Optional;
import java.util.Set;

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


    public Page<Course> getAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    public Page<Course> getAllByCategory(String categoryName, Pageable pageable) {
        Category category = categoryService.getCategoryByName(categoryName);
        return courseRepository.findByCategory(category, pageable);
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

    private void saveNewTags(CourseDto courseDto) {
        Optional.ofNullable(courseDto.getTags())
                .ifPresent(tags -> tagService.saveNewTags(courseDto.getTags()));
    }

    private void increaseCorrespondingCounts(Course course) {
        tagService.increaseCount(course.getTags());
        updateCategoryCount(course.getCategory());
    }

    private void notifyInstructorAboutCreatedCourse(User instructor) {
        mailService.notifyUser(instructor, MailType.NEW_COURSE);
    }

    public void deleteCourse(Long id) {
        Course courseToDelete = getById(id);
        Set<Tag> tagsOfDeletedCourse = courseToDelete.getTags();
        Category categoryOfDeletedCourse = courseToDelete.getCategory();
        User courseInstructor = courseToDelete.getInstructor();

        courseRepository.delete(courseToDelete);

        decreaseCorrespondingCounts(categoryOfDeletedCourse, tagsOfDeletedCourse);
        notifyInstructorAboutDeletedCourse(courseInstructor);
    }

    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @Async
    void decreaseCorrespondingCounts(Category category, Set<Tag> tags) {
        tagService.decreaseCount(tags);
        updateCategoryCount(category);
    }

    private void updateCategoryCount(Category category) {
        Integer numberOfCoursesOfTheCategory = courseRepository.countAllByCategory(category);
        category.setCount(numberOfCoursesOfTheCategory);
        categoryService.updateCategory(category);
    }

    @Async
    void notifyInstructorAboutDeletedCourse(User instructor) {
        mailService.notifyUser(instructor, MailType.DELETED_COURSE);
    }
}
