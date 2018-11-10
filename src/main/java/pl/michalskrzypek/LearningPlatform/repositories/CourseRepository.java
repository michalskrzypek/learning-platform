package pl.michalskrzypek.LearningPlatform.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course,Long> {
    Page<Course> findByCategory(Category category, Pageable pageable);
    Page<Course> findAll(Pageable pageable);
}
