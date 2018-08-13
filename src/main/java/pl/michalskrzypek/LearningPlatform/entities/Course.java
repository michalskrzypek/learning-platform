package pl.michalskrzypek.LearningPlatform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String title;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(name = "course_tag",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private User instructor;

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<User> assigned_students;

    @JoinColumn(name = "course_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;
}
