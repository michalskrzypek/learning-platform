package pl.michalskrzypek.LearningPlatform.services.mails;

public interface IEmailService {

    void sendMail(String to, String subject, String body);
}
