package pl.michalskrzypek.LearningPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.michalskrzypek.LearningPlatform.entities.Course;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course,Long> {

}
