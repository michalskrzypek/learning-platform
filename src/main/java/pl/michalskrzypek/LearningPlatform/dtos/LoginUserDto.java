package pl.michalskrzypek.LearningPlatform.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginUserDto {

    @NotNull(message = "Please provide the username!")
    private String username;

    @NotNull(message = "Please provide the password!")
    private String password;
}
