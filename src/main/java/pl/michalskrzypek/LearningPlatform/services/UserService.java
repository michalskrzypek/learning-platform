package pl.michalskrzypek.LearningPlatform.services;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public void saveUser(User newUser){
        userRepository.save(newUser);
    }
}
