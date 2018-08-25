package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.michalskrzypek.LearningPlatform.dtos.LoginUser;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.services.UserService;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerNewUser(@RequestBody LoginUser newUser){

        User registeredUser = new User();
        registeredUser.setEmail(newUser.getUsername());
        registeredUser.setPassword(encoder.encode(newUser.getPassword()));

        userService.saveUser(registeredUser);
        return registeredUser;
    }

}
