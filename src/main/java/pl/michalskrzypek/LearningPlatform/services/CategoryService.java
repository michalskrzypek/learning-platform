package pl.michalskrzypek.LearningPlatform.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.repositories.CategoryRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CategoryService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(String sortProperty) {
/*        logger.warn("HERE0" + sortProperty);
        Stream<String> categoryProperties = getCategoryFields().stream();
        logger.warn(categoryProperties.findFirst().get());
        boolean inProperties = categoryProperties.anyMatch(property -> property.equalsIgnoreCase(sortProperty));
        logger.warn(inProperties);
        if (inProperties) {*/
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll(Sort.by(sortProperty).descending()).forEach(category -> categories.add(category));
            /*logger.warn("HERE");
    } else {
        return getAllCategories();
    }*/
        return categories;
    }


    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categories.add(category));
        return categories;
    }

/*    private List<String> getCategoryFields(){
        Field[] allFields = Category.class.getFields();
        List<String> fieldsNames = new ArrayList<>();
        for(Field f : allFields){
            fieldsNames.add(f.getName());
        }
        logger.warn(fieldsNames);
        return fieldsNames;
    }*/

}
