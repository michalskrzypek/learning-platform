package pl.michalskrzypek.LearningPlatform.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ErrorDetails {

    @NotNull
    private String message;

    private Date timestamp;

    private String details;
}
