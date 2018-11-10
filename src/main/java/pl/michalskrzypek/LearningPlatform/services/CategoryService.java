package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.exceptions.CategoryNotFoundException;
import pl.michalskrzypek.LearningPlatform.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(String sortProperty, boolean descending) {
        if (descending) {
            return categoryRepository.findAll(Sort.by(sortProperty).descending());
        } else {
            return categoryRepository.findAll(Sort.by(sortProperty).ascending());
        }
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName).orElseThrow(() -> new CategoryNotFoundException(categoryName));
    }

    public void increaseCount(Category category, int incValue) {
        int newCount = category.getCount() + incValue;
        category.setCount(newCount);
        categoryRepository.save(category);
    }
}
