package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import pl.michalskrzypek.LearningPlatform.common.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService implements IMailService {

    JavaMailSender mailSender;

    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.mailSender = javaMailSender;
    }

    @Override
    public void sendMail(Mail mail) {
        try {
            tryToSendMessage(mail);
        } catch (MessagingException exception) {
        //TODO notify user about error
        }
    }

    private void tryToSendMessage(Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setTo(mail.getRecipient());
        messageHelper.setSubject(mail.getSubject());
        messageHelper.setText(mail.getHtmlText(), true);

        mailSender.send(message);
    }
}
