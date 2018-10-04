package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.config.MailConfig;

@Component
public class NewCourseMailTemplate extends MailTemplate {

    MailConfig mailConfig;

    public NewCourseMailTemplate(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
/*
        Context context = new Context();
        context.setVariable("courseTitle", course.getTitle());
        context.setVariable("category", course.getCategory());
        this.setContext(context);*/
    }

    @Override
    public MailTemplate create(){
        MailConfig.MailConfigEntry courseCreatedMailConfig = mailConfig.getCourseCreated();
        this.setSubject(courseCreatedMailConfig.getTitle());
        this.setTemplateName(courseCreatedMailConfig.getTemplateName());
        return this;
    }
}
