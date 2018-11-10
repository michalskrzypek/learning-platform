package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.dtos.CourseDto;
import pl.michalskrzypek.LearningPlatform.dtos.converters.CourseDtoConverter;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.enums.MailType;
import pl.michalskrzypek.LearningPlatform.exceptions.CourseNotFoundException;
import pl.michalskrzypek.LearningPlatform.repositories.CourseRepository;
import pl.michalskrzypek.LearningPlatform.services.mails.MailService;

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

<<<<<<< HEAD
    public Course save(CourseDto courseDto) {
        Course course = courseDtoConverter.convert(courseDto);

        User instructor = userService.getCurrentUser();
        course.setInstructor(instructor);
        courseRepository.save(course);

        increaseCorrespondingCounts(course);

        mailService.notifyUser(instructor, MailType.NEW_COURSE);
        return course;
    }

    public Page<Course> getAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
=======
    public List<Course> findAll() {
        List<Course> allCourses = new ArrayList<>();
        courseRepository.findAll().forEach(c -> allCourses.add(c));
        return allCourses;
>>>>>>> features-course-deletion
    }

    public Page<Course> getAllByCategory(String categoryName, Pageable pageable) {
        Category category = categoryService.getCategoryByName(categoryName);
        return courseRepository.findByCategory(category, pageable);
    }

<<<<<<< HEAD
    public Course getById(Long id) {
        return courseRepository.findById(id)
=======
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
        categoryService.increaseCount(course.getCategory());
        tagService.increaseCount(course.getTags());
    }

    private void notifyInstructorAboutCreatedCourse(User instructor) {
        mailService.notifyUser(instructor, MailType.NEW_COURSE);
    }

    public void deleteCourse(Long id) {
        Course courseToDelete = findById(id);
        decreaseCorrespondingCounts(courseToDelete);
        notifyInstructorAboutDeletedCourse(courseToDelete.getInstructor());
        courseRepository.delete(courseToDelete);
    }

    public Course findById(Long id) {
        Course course = courseRepository.findById(id)
>>>>>>> features-course-deletion
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

<<<<<<< HEAD
    public void increaseCorrespondingCounts(Course course) {
        tagService.addCount(course.getTags());
        updateCategoryCount(course.getCategory());
=======
    private void decreaseCorrespondingCounts(Course course) {
        categoryService.decreaseCount(course.getCategory());
        tagService.decreaseCount(course.getTags());
    }

    private void notifyInstructorAboutDeletedCourse(User instructor) {
        mailService.notifyUser(instructor, MailType.DELETED_COURSE);
>>>>>>> features-course-deletion
    }

    private void updateCategoryCount(Category category){
        int numberOfCoursesOfTheCategory = courseRepository.countAllByCategory(category);
        category.setCount(numberOfCoursesOfTheCategory);
    }

}
