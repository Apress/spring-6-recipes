package com.apress.spring6recipes.sequence.config;

import com.apress.spring6recipes.sequence.PrefixGenerator;
import com.apress.spring6recipes.sequence.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PrefixConfiguration.class)
public class SequenceConfiguration {

	@Bean
	public Sequence sequence(PrefixGenerator prefixGenerator) {
		return new Sequence(prefixGenerator, "A", 100000);
	}
}
