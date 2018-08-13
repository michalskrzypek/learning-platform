package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.services.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id).get();
    }

}