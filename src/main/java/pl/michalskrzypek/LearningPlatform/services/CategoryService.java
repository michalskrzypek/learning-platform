package pl.michalskrzypek.LearningPlatform.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.exceptions.CategoryNotFoundException;
import pl.michalskrzypek.LearningPlatform.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories(String sortProperty, boolean descending) {
        List<Category> categories = new ArrayList<>();
        if(descending) {
            categoryRepository.findAll(Sort.by(sortProperty).descending()).forEach(category -> categories.add(category));
        }else{
            categoryRepository.findAll(Sort.by(sortProperty).ascending()).forEach(category -> categories.add(category));
        }
        return categories;
    }

    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categories.add(category));
        return categories;
    }

    public Category findByName(String categoryName){
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
        Category category = optionalCategory.orElseThrow(() -> new CategoryNotFoundException(categoryName));
        return category;
    }
}
