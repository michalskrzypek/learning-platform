package pl.michalskrzypek.LearningPlatform;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class LearningPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningPlatformApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		ImmutableList<ClassPathResource> resources = ImmutableList.of(
				new ClassPathResource("categories.yml"),
				new ClassPathResource("mails.yml"));
		yaml.setResources(Iterables.toArray(resources, ClassPathResource.class));
		propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
		return propertySourcesPlaceholderConfigurer;
	}
}