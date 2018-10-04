package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.entities.Course;
import pl.michalskrzypek.LearningPlatform.enums.MailType;

@Component
public class MailTemplateFactory {

    @Autowired
    private NewCourseMailTemplate newCourseMailTemplate;

    public MailTemplate createMailTemplate(MailType mailType) {
        MailTemplate mailTemplate = null;
        switch (mailType) {
            case NEW_COURSE:
//                Course course = (Course) contextElement;
                mailTemplate = newCourseMailTemplate.create();
        }
        return mailTemplate;
    }
}
