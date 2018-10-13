package pl.michalskrzypek.LearningPlatform.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User does not exist!");
    }
}
