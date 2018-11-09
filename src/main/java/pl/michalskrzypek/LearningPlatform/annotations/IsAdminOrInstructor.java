package pl.michalskrzypek.LearningPlatform.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@PreAuthorize("hasAnyRole(T(pl.michalskrzypek.LearningPlatform.common.Roles).ROLE_ADMIN, T(pl.michalskrzypek.LearningPlatform.common.Roles).ROLE_INSTRUCTOR)")
public @interface IsAdminOrInstructor {
}
