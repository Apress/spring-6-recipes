package com.apress.spring6recipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring6recipes.sequence.PrefixGenerator;
import com.apress.spring6recipes.sequence.Sequence;
import com.apress.spring6recipes.sequence.DatePrefixGenerator;

@Configuration
public class SequenceConfiguration {

	@Bean
	public DatePrefixGenerator datePrefixGenerator() {
		return new DatePrefixGenerator("yyyyMMdd");
	}

	@Bean
	public Sequence sequenceGenerator(PrefixGenerator prefixGenerator) {
		return new Sequence(prefixGenerator, "A", 100000);
	}

}
