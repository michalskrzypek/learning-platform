package pl.michalskrzypek.LearningPlatform.services.mails;

import pl.michalskrzypek.LearningPlatform.common.Mail;

public interface IMailService {

    void sendMail(Mail mail);
}
