package com.apress.spring6recipes.sequence.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceConfiguration {

	/**
	 * @Bean public DatePrefixGenerator datePrefixGenerator() { DatePrefixGenerator dpg =
	 * new DatePrefixGenerator(); dpg.setPattern("yyyyMMdd"); return dpg; }
	 * @Bean public SequenceGenerator sequenceGenerator() { SequenceGenerator sequence=
	 * new SequenceGenerator(); sequence.setInitial(100000); //sequence.setSuffix("A");
	 * sequence.setPrefixGenerator(datePrefixGenerator()); return sequence; }
	 */

}
