package com.apress.spring6recipes.bank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class BankConfiguration {

	@Bean
	public InMemoryAccountDao accountDao() {
		return new InMemoryAccountDao();
	}

}
