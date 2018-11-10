package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.dtos.UserDto;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.exceptions.UserNotFoundException;
import pl.michalskrzypek.LearningPlatform.repositories.CourseRepository;
import pl.michalskrzypek.LearningPlatform.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private CourseService courseService;

    public UserService(UserRepository userRepository, CourseRepository courseRepository, CourseService courseService) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.courseService = courseService;
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

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    public User saveUser(UserDto newUser) {
        User registeredUser = new User();
        registeredUser.setEmail(newUser.getEmail());
        registeredUser.setPassword(encoder.encode(newUser.getPassword()));
        registeredUser.setFirstName(newUser.getFirstName());
        registeredUser.setLastName(newUser.getLastName());
        registeredUser.setRole(newUser.getRole());
        return userRepository.save(registeredUser);
    }

    public List<Course> getCoursesOfTheCurrentUser() {
        User currentUser = getCurrentUser();
        return currentUser.getAssigned_courses();
    }

    public Course assignCourseToTheCurrentUser(Long courseId) {
        Course courseToAssign = courseService.getById(courseId);

        User currentUser = getCurrentUser();
        currentUser.getAssigned_courses().add(courseToAssign);

        courseToAssign.getAssigned_users().add(currentUser);
        courseService.increaseEnrollments(courseToAssign, 1);
        courseRepository.save(courseToAssign);
        userRepository.save(currentUser);
        return courseToAssign;
    }

}
