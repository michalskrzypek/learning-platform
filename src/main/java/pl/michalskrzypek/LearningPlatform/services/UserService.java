package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.dtos.UserDto;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("User " + user.getUsername() + " does not exist!");
        }

        return user;
    }

    public List<User> findAll() {

        List<User> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(u -> allUsers.add(u));

        return allUsers;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(UserDto newUser){

        User registeredUser = new User();
        registeredUser.setEmail(newUser.getEmail());
        registeredUser.setPassword(encoder.encode(newUser.getPassword()));
        registeredUser.setFirstName(newUser.getFirstName());
        registeredUser.setLastName(newUser.getLastName());
        registeredUser.setRole(newUser.getRole());

        return userRepository.save(registeredUser);
    }
}
