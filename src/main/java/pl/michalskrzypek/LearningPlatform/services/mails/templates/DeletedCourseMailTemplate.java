package pl.michalskrzypek.LearningPlatform.services.mails.templates;

import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.config.MailConfig;
import pl.michalskrzypek.LearningPlatform.services.mails.MailTemplate;

@Component
public class DeletedCourseMailTemplate extends MailTemplate {

    MailConfig mailConfig;

    public DeletedCourseMailTemplate(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    @Override
    public MailTemplate create(){
        MailConfig.MailConfigEntry courseCreatedMailConfig = mailConfig.getCourseDeleted();
        this.setSubject(courseCreatedMailConfig.getTitle());
        this.setTemplateName(courseCreatedMailConfig.getTemplateName());
        return this;
    }
}
