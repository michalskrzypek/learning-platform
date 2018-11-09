package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.config.MailConfig;

@Component
public class NewCourseMailTemplate extends MailTemplate {

    MailConfig mailConfig;

    public NewCourseMailTemplate(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    @Override
    public MailTemplate create(){
        MailConfig.MailConfigEntry courseCreatedMailConfig = mailConfig.getCourseCreated();
        this.setSubject(courseCreatedMailConfig.getTitle());
        this.setTemplateName(courseCreatedMailConfig.getTemplateName());
        return this;
    }
}
