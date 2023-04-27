package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.EmailErrorNotifier;
import com.apress.spring6recipes.replicator.ErrorNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfiguration {

	@Bean
	public ErrorNotifier errorNotifier() {
		return new EmailErrorNotifier();
	}
}
