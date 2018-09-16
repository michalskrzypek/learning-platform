package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "{\"message\": \"Hello it is a message from controller ;)\"}";
    }
}
