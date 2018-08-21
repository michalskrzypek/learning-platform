package pl.michalskrzypek.LearningPlatform.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private int grade;

    @NotNull
    private String comment;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();
}
