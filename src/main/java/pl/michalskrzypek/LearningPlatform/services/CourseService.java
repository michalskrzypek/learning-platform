package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.repositories.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private CategoryService categoryService;

    public CourseService(CourseRepository courseRepository, CategoryService categoryService) {
        this.courseRepository = courseRepository;
        this.categoryService = categoryService;
    }

    public List<Course> findAll(){
        List<Course> allCourses = new ArrayList<>();
        courseRepository.findAll().forEach(c -> allCourses.add(c));
        return allCourses;
    }

    public List<Course> findAllByCategory(String categoryName){
        List<Course> courses = new ArrayList<>();
        Category category = categoryService.findByName(categoryName);
        courseRepository.findByCategory(category).forEach(c -> courses.add(c));
        return courses;
    }

    public Course findById(Long id){
        return courseRepository.findById(id).get();
    }

}
