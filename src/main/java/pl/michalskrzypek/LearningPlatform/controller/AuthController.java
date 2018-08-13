package pl.michalskrzypek.LearningPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.michalskrzypek.LearningPlatform.common.AuthToken;
import pl.michalskrzypek.LearningPlatform.entity.User;
import pl.michalskrzypek.LearningPlatform.model.LoginUser;
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

    @PostMapping(value = "/generate-token")
    public ResponseEntity register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User authenticatedUser = (User) userService.loadUserByUsername(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(authenticatedUser);
        return ResponseEntity.ok(new AuthToken(token));
    }

}
