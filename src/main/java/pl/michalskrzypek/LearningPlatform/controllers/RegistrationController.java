package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.michalskrzypek.LearningPlatform.dtos.UserDto;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerNewUser(@Valid @RequestBody UserDto newUser){
        return userService.saveUser(newUser);
    }
}
