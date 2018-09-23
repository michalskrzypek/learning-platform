package pl.michalskrzypek.LearningPlatform.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CourseDto {

    @NotNull(message = "Please provide title.")
    private String title;
    private String description = "No description available.";
    @NotNull(message = "Please provide category.")
    private String category;
    private List<String> tags;
}
