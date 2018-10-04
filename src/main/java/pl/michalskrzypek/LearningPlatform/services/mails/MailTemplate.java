package pl.michalskrzypek.LearningPlatform.services.mails;

import lombok.Data;
import org.thymeleaf.context.Context;

@Data
public abstract class MailTemplate {

    private String subject;
    private String templateName;
//    private Context context;
    public abstract MailTemplate create();
}
