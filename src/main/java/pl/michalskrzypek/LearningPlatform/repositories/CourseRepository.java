package pl.michalskrzypek.LearningPlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michalskrzypek.LearningPlatform.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
