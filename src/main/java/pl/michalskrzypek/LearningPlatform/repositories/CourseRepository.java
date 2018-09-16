package pl.michalskrzypek.LearningPlatform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.entities.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course,Long> {
    Iterable<Course> findByCategory(Category category);
}
