package pl.michalskrzypek.LearningPlatform.services.mails;

import lombok.Data;

@Data
public abstract class MailTemplate {

    private String subject;
    private String templateName;

    public abstract MailTemplate create();
}
