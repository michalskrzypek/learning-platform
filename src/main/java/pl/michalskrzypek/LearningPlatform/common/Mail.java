package pl.michalskrzypek.LearningPlatform.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Mail {

    private String recipient;
    private String subject;
    private String htmlText;
}
