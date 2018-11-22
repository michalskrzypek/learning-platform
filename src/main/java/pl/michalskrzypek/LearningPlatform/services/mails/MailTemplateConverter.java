package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.michalskrzypek.LearningPlatform.common.Mail;

/**
 * Class responsible for processing MailTemplates to the final reade-to-sent mails of the class Mail
 */
@Component
public class MailTemplateConverter {

    private TemplateEngine templateEngine;

    public MailTemplateConverter(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public Mail createMail(String recipient, MailTemplate mailTemplate) {
        String mailBody = processTemplateBody(mailTemplate.getTemplateName());
        String subject = mailTemplate.getSubject();

        return Mail.of(recipient, subject, mailBody);
    }

    private String processTemplateBody(String templateName) {
        String body = templateEngine.process(templateName, new Context());
        return body;
    }
}
