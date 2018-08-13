package pl.michalskrzypek.LearningPlatform.common;

import pl.michalskrzypek.LearningPlatform.entities.User;

public class Roles {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_INSTRUCTOR = "ROLE_INSTRUCTOR";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static boolean is(User user, String role) {
        return user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role));
    }
}