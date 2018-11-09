package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.enums.MailType;
import pl.michalskrzypek.LearningPlatform.services.mails.MailTemplate;
import pl.michalskrzypek.LearningPlatform.services.mails.templates.DeletedCourseMailTemplate;
import pl.michalskrzypek.LearningPlatform.services.mails.templates.NewCourseMailTemplate;

@Component
public class MailTemplateFactory {

    @Autowired
    private NewCourseMailTemplate newCourseMailTemplate;

    @Autowired
    private DeletedCourseMailTemplate deletedCourseMailTemplate;

    public MailTemplate createMailTemplate(MailType mailType) {
        MailTemplate mailTemplate = null;
        switch (mailType) {
            case NEW_COURSE:
                mailTemplate = newCourseMailTemplate.create();
                break;
            case DELETED_COURSE:
                mailTemplate = deletedCourseMailTemplate.create();
                break;
        }
        return mailTemplate;
    }
}
