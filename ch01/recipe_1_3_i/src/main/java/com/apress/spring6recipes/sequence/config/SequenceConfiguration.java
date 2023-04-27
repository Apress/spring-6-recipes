package com.apress.spring6recipes.sequence.config;

import com.apress.spring6recipes.sequence.PrefixGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring6recipes.sequence.DatePrefixGenerator;
import com.apress.spring6recipes.sequence.Sequence;

@Configuration
public class SequenceConfiguration {

	@Bean
	public DatePrefixGenerator datePrefixGenerator() {
		return new DatePrefixGenerator("yyyyMMdd");
	}

	@Bean
	public Sequence sequenceGenerator(PrefixGenerator prefixGenerator) {
		var generator = new Sequence("A", 100000);
		generator.setPrefixGenerator(prefixGenerator);
		return generator;
	}
}
