package pl.michalskrzypek.LearningPlatform.dtos.converters;

import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.dtos.UserDto;
import pl.michalskrzypek.LearningPlatform.entities.User;

@Component
public class UserDtoConverter {

    public User convert(UserDto userDto) {
        User registeredUser = new User();
        registeredUser.setEmail(userDto.getEmail());
        registeredUser.setFirstName(userDto.getFirstName());
        registeredUser.setLastName(userDto.getLastName());
        registeredUser.setRole(userDto.getRole());
        return registeredUser;
    }
}
