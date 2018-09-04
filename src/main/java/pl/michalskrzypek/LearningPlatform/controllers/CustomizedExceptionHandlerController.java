package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.michalskrzypek.LearningPlatform.common.ErrorDetails;

import java.util.Date;

@ControllerAdvice
public class CustomizedExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDetails invalidFormArgsException(MethodArgumentNotValidException exception) {

        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        return ErrorDetails.builder().message(errorMsg).timestamp(new Date()).build();
    }
}
