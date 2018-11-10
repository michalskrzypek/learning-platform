package pl.michalskrzypek.LearningPlatform.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Page<Course> findByCategory(Category category, Pageable pageable);
    Page<Course> findAll(Pageable pageable);
}
