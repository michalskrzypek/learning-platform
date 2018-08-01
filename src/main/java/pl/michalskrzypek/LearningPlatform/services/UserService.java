package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.entity.User;
import pl.michalskrzypek.LearningPlatform.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(s);

        if(user == null){
            throw new UsernameNotFoundException("User " + user.getUsername() + " does not exist!");
        }

        return user;
    }
}
