package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.dtos.UserDto;
import pl.michalskrzypek.LearningPlatform.dtos.converters.UserDtoConverter;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.exceptions.UserNotFoundException;
import pl.michalskrzypek.LearningPlatform.repositories.CourseRepository;
import pl.michalskrzypek.LearningPlatform.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private UserDtoConverter userDtoConverter;
    private UserRepository userRepository;
    private CourseRepository courseRepository;

    public UserService(UserDtoConverter userDtoConverter, UserRepository userRepository, CourseRepository courseRepository) {
        this.userDtoConverter = userDtoConverter;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public User getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = (User) loadUserByUsername(userName);
        return user;
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

    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    public User saveUser(UserDto newUser) {
        User registeredUser = userDtoConverter.convert(newUser);
        registeredUser.setPassword(encoder.encode(newUser.getPassword()));
        return userRepository.save(registeredUser);
    }

    public List<Course> findAllCourses() {
        User currentUser = getCurrentUser();
        return currentUser.getAssigned_courses();
    }

    public Course assignCourse(Course course) {
        User currentUser = getCurrentUser();
        currentUser.getAssigned_courses().add(course);
        course.getAssigned_users().add(currentUser);
        userRepository.save(currentUser);
        courseRepository.save(course);
        return course;
    }
}
