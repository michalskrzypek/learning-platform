package pl.michalskrzypek.LearningPlatform.services.mails;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService {

    JavaMailSender mailSender;
    
    public EmailService(JavaMailSender javaMailSender){
        this.mailSender = javaMailSender;
    }

    @Override
    public void sendMail(String to, String subject, String body) {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper mailHelper = null;
        try {
            mailHelper = new MimeMessageHelper(mail, true);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mail);
    }
}
