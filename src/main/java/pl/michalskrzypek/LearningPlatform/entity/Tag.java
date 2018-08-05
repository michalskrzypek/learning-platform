package pl.michalskrzypek.LearningPlatform.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    private int count = 0;
}
