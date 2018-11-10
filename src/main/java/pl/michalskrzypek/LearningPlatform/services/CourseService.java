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

        increaseCorrespondingCounts(course);

        mailService.notifyUser(instructor, MailType.NEW_COURSE);
        return course;
    }

    public Page<Course> getAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    public Page<Course> getAllByCategory(String categoryName, Pageable pageable) {
        Category category = categoryService.getCategoryByName(categoryName);
        return courseRepository.findByCategory(category, pageable);
    }

    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    public void increaseCorrespondingCounts(Course course) {
        tagService.addCount(course.getTags());
        updateCategoryCount(course.getCategory());
    }

    private void updateCategoryCount(Category category){
        int numberOfCoursesOfTheCategory = courseRepository.countAllByCategory(category);
        category.setCount(numberOfCoursesOfTheCategory);
    }

}
