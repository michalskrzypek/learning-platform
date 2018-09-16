package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Value("${categories.sort.property}")
    private String defaultSortProperty;

    private CategoryService categoryService;

    public CategoryController (CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(params = {"sort", "desc"})
    public List<Category> getAllCategoriesSorted(@RequestParam("sort") String sortProperty, @RequestParam("desc") boolean descending){
        return categoryService.findAllCategories(sortProperty, descending);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.findAllCategories();
    }
}
