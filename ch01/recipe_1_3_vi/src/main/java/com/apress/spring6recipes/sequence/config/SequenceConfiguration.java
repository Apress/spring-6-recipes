package com.apress.spring6recipes.sequence.config;

import com.apress.spring6recipes.sequence.DatePrefixGenerator;
import com.apress.spring6recipes.sequence.NumberPrefixGenerator;
import com.apress.spring6recipes.sequence.PrefixGenerator;
import com.apress.spring6recipes.sequence.Sequence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public Sequence sequenceGenerator(@Qualifier("datePrefixGenerator")
																			PrefixGenerator prefixGenerator) {
		return new Sequence(prefixGenerator, "A", 100000);
	}

}
