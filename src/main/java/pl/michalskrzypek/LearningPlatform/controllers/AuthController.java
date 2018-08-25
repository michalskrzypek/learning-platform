package pl.michalskrzypek.LearningPlatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.michalskrzypek.LearningPlatform.common.AuthToken;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.dtos.LoginUserDto;
import pl.michalskrzypek.LearningPlatform.services.UserService;
import pl.michalskrzypek.LearningPlatform.utils.JWTTokenUtil;

@RestController
@RequestMapping("/token")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/generate-token")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthToken authenticateUser(@RequestBody LoginUserDto loginUser) throws AuthenticationException {

        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User authenticatedUser = (User) userService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(authenticatedUser);
        return new AuthToken(token);
    }
}
