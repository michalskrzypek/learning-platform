package pl.michalskrzypek.LearningPlatform.dtos;

import lombok.Data;
import pl.michalskrzypek.LearningPlatform.entities.Category;
import pl.michalskrzypek.LearningPlatform.entities.Tag;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CourseDto {

    @NotNull
    private String title;
    private String description = "No description available.";
    @NotNull
    private String category;
    private List<String> tags;
}
