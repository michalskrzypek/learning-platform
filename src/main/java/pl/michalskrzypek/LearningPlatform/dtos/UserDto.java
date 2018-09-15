package pl.michalskrzypek.LearningPlatform.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    @Email(message = "Please provide valid email address.")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Password must contain at least 8 characters, including one number.")
    protected String password;
    @NotNull(message = "Please provide your first name.")
    private String firstName;
    @NotNull(message = "Please provide you last name.")
    private String lastName;
    @NotNull
    private String role;
}
