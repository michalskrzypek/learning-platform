package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.common.Mail;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.enums.MailType;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private JavaMailSender mailSender;
    private MailTemplateFactory mailTemplateFactory;
    private MailTemplateConverter mailTemplateConverter;

    public MailService(JavaMailSender javaMailSender, MailTemplateFactory mailTemplateFactory, MailTemplateConverter mailTemplateConverter){
        this.mailSender = javaMailSender;
        this.mailTemplateFactory = mailTemplateFactory;
        this.mailTemplateConverter = mailTemplateConverter;
    }

    public void notifyUser(User user, MailType mailType) {
        MailTemplate mailTemplate = mailTemplateFactory.createMailTemplate(mailType);
        Mail mail = mailTemplateConverter.createMail(user.getEmail(), mailTemplate);
        sendMail(mail);
    }

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
