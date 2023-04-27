package com.apress.spring6recipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring6recipes.sequence.Sequence;

@Configuration
public class SequenceConfiguration {

	@Bean
	public Sequence sequence() {
		var sequence = new Sequence();
		sequence.setInitial(100000);
		sequence.setSuffix("A");
		return sequence;
	}

}
