package pl.michalskrzypek.LearningPlatform.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("mails")
public class MailConfig {

    private MailConfigEntry courseCreated;

    @Data
    public static class MailConfigEntry {
        private String title;
        private String templateName;
    }
}
