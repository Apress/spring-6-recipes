package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.EmailErrorNotifier;
import com.apress.spring6recipes.replicator.ErrorNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

	@Bean
	public ErrorNotifier errorNotifier(JavaMailSender mailSender) {
		return new EmailErrorNotifier(mailSender);
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
}
