package pl.michalskrzypek.LearningPlatform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.michalskrzypek.LearningPlatform.entities.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
