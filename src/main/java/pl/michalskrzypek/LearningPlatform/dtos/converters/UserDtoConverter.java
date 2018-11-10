package pl.michalskrzypek.LearningPlatform.dtos.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.dtos.UserDto;
import pl.michalskrzypek.LearningPlatform.entities.User;

@Component
public class UserDtoConverter {

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User convert(UserDto userDto) {
        User registeredUser = new User();
        registeredUser.setEmail(userDto.getEmail());
        registeredUser.setPassword(encoder.encode(userDto.getPassword()));
        registeredUser.setFirstName(userDto.getFirstName());
        registeredUser.setLastName(userDto.getLastName());
        registeredUser.setRole(userDto.getRole());
        return registeredUser;
    }
}
