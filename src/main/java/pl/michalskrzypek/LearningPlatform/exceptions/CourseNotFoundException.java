package pl.michalskrzypek.LearningPlatform.exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException() {
        super();
    }

    public CourseNotFoundException(Long id) {
        super("Course with id: " + id + " couldn't be found!");
    }
}
