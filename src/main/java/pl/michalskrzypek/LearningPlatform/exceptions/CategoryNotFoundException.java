package pl.michalskrzypek.LearningPlatform.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(){
        super();
    }

    public CategoryNotFoundException(String categoryName){
        super("Category of name \""+ categoryName +"\" couldn't be found!");
    }
}
