package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.michalskrzypek.LearningPlatform.common.ErrorDetails;
import pl.michalskrzypek.LearningPlatform.exceptions.CategoryNotFoundException;
import pl.michalskrzypek.LearningPlatform.exceptions.CourseNotFoundException;

import java.util.Date;

@ControllerAdvice
@ResponseBody
public class CustomizedExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails invalidFormArgsException(MethodArgumentNotValidException exception) {
        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());
        return ErrorDetails.builder().message(errorMsg).timestamp(new Date()).build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails invalidCredentialsException() {
        return ErrorDetails.builder().message("Wrong user credentials!").timestamp(new Date()).build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails invalidUsernameException(UsernameNotFoundException exception) {
        String message = exception.getMessage();
        return ErrorDetails.builder().message(message).timestamp(new Date()).build();
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails invalidCategoryName(CategoryNotFoundException exception){
        String message = exception.getMessage();
        return ErrorDetails.builder().message(message).build();
    }

    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails invalidCategoryName(CourseNotFoundException exception){
        String message = exception.getMessage();
        return ErrorDetails.builder().message(message).build();
    }
}