package fr.diginamic.webmvc01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class BiblioThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioThymeleafApplication.class, args);
	}

	/**
	 * Configuration pour le chargement des messages Intenationaux
	 * messages.properties
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		messageSource.setDefaultEncoding("ISO-8859-1");
		return messageSource;
	}
	
}
