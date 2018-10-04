package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.michalskrzypek.LearningPlatform.common.Mail;

/**
 * Class responsible for processing MailTemplates to final Mails
 */
@Component
public class MailTemplateConverter {

    private static TemplateEngine templateEngine;

    public MailTemplateConverter(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public static Mail createMail(String recipient, MailTemplate mailTemplate) {
        String mailBody = processTemplateBody(mailTemplate.getTemplateName());
        String subject = mailTemplate.getSubject();

        return Mail.of(recipient, subject, mailBody);
    }

    private static String processTemplateBody(String templateName) {
        String body = templateEngine.process(templateName, new Context());
        return body;
    }
}
