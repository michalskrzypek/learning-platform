package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public List listUser() {
        return userService.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public User getOne(@PathVariable(value = "id") Long id) {
        return userService.findById(id).get();
    }

    @GetMapping("/current")
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }
}