package pl.michalskrzypek.LearningPlatform.dtos.converters;

import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.dtos.CourseDto;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.entities.Tag;
import pl.michalskrzypek.LearningPlatform.repositories.CourseRepository;
import pl.michalskrzypek.LearningPlatform.services.CategoryService;
import pl.michalskrzypek.LearningPlatform.services.TagService;
import pl.michalskrzypek.LearningPlatform.services.UserService;

import java.util.List;
import java.util.Optional;

@Component
public class CourseDtoConverter {

    private CategoryService categoryService;
    private TagService tagService;
    private UserService userService;

    public CourseDtoConverter(CourseRepository courseRepository, CategoryService categoryService, TagService tagService, UserService userService) {
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.userService = userService;
    }

    public Course convert(CourseDto from){
        Course course = new Course();
        course.setTitle(from.getTitle());
        course.setDescription(from.getDescription());

        Category category = categoryService.findByName(from.getCategory());
        course.setCategory(category);

        Optional.ofNullable(from.getTags()).ifPresent(tags -> {
            List<Tag> courseTags = tagService.getAllByNames(tags);
            course.setTags(courseTags);
        });

        return course;
    }
}
