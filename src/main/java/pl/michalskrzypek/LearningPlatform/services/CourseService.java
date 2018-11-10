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

    public Course save(CourseDto courseDto) {
        Course course = courseDtoConverter.convert(courseDto);

        User instructor = userService.getCurrentUser();
        course.setInstructor(instructor);
        courseRepository.save(course);

        mailService.notifyUser(instructor, MailType.NEW_COURSE);
        return course;
    }

    public Page<Course> findAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    public Page<Course> findAllByCategory(String categoryName, Pageable pageable) {
        Category category = categoryService.findByName(categoryName);
        return courseRepository.findByCategory(category, pageable);
    }

    public Course findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        return course;
    }

    public void increaseCorrespondingCounts(Course course) {
        categoryService.addCount(course.getCategory());
        tagService.addCount(course.getTags());
    }
}
