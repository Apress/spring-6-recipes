package com.apress.spring6recipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring6recipes.sequence.DatePrefixGenerator;
import com.apress.spring6recipes.sequence.NumberPrefixGenerator;
import com.apress.spring6recipes.sequence.Sequence;

@Configuration
public class SequenceConfiguration {

	@Bean
	public DatePrefixGenerator datePrefixGenerator() {
		var dpg = new DatePrefixGenerator();
		dpg.setPattern("yyyyMMdd");
		return dpg;
	}

	@Bean
	public NumberPrefixGenerator numberPrefixGenerator() {
		return new NumberPrefixGenerator();
	}

	@Bean
	public Sequence sequenceGenerator() {
		var sequence = new Sequence();
		sequence.setInitial(100000);
		sequence.setSuffix("A");
		return sequence;
	}

}
