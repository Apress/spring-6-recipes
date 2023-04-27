package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.EmailErrorNotifier;
import com.apress.spring6recipes.replicator.ErrorNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

	private static final String MSG = """
					       Dear Administrator,
					       An error occurred when copying the following file :
					       Source directory: %s
					       Destination directory: %s
					       Filename : %s
					""";

	@Bean
	public ErrorNotifier errorNotifier(JavaMailSender mailSender,
																		 SimpleMailMessage template) {
		return new EmailErrorNotifier(mailSender, template);
	}

	@Bean
	public JavaMailSenderImpl mailSender() {
		var mailSender = new JavaMailSenderImpl();
		mailSender.setHost("localhost");
		mailSender.setPort(3025);
		mailSender.setUsername("system");
		mailSender.setPassword("12345");
		return mailSender;
	}

	@Bean
	public SimpleMailMessage copyErrorMailMessage() {
		var message = new SimpleMailMessage();
		message.setFrom("system@localhost");
		message.setTo("admin@localhost");
		message.setSubject("File Copy Error");
		message.setText(MSG);
		return message;
	}
}
