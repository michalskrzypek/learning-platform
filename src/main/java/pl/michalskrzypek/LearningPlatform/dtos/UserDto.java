package pl.michalskrzypek.LearningPlatform.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
}
