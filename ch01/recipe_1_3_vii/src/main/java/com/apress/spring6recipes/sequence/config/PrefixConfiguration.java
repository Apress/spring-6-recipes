package com.apress.spring6recipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring6recipes.sequence.DatePrefixGenerator;

@Configuration
public class PrefixConfiguration {

	@Bean
	public DatePrefixGenerator datePrefixGenerator() {
		return new DatePrefixGenerator("yyyyMMdd");
	}
}
