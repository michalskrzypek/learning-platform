package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.enums.MailType;
import pl.michalskrzypek.LearningPlatform.services.mails.templates.DeletedCourseMailTemplate;
import pl.michalskrzypek.LearningPlatform.services.mails.templates.NewCourseMailTemplate;

@Component
public class MailTemplateFactory {

    private NewCourseMailTemplate newCourseMailTemplate;
    private DeletedCourseMailTemplate deletedCourseMailTemplate;

    MailTemplateFactory(NewCourseMailTemplate newCourseMailTemplate, DeletedCourseMailTemplate deletedCourseMailTemplate) {
        this.newCourseMailTemplate = newCourseMailTemplate;
        this.deletedCourseMailTemplate = deletedCourseMailTemplate;
    }

    public MailTemplate createMailTemplate(MailType mailType) {
        switch (mailType) {
            case NEW_COURSE:
                return newCourseMailTemplate.create();
            case DELETED_COURSE:
                return deletedCourseMailTemplate.create();
            default:
                throw new UnsupportedOperationException("No such mail type");
        }
    }
}
