package com.apress.spring6recipes.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

	@Bean
	public MapReactiveUserDetailsService userDetailsRepository() {
		var marten = User.withDefaultPasswordEncoder()
						.username("marten").password("secret").authorities("USER").build();
		var admin = User.withDefaultPasswordEncoder()
						.username("admin").password("admin").authorities("USER", "ADMIN").build();
		return new MapReactiveUserDetailsService(marten, admin);
	}
}
