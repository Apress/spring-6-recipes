package com.apress.spring6recipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.apress.spring6recipes.sequence.Sequence;

@Configuration
public class SequenceConfiguration {

	@Bean
	@DependsOn("datePrefixGenerator")
	public Sequence sequenceGenerator() {
		return new Sequence("A", 100000);
	}
}
