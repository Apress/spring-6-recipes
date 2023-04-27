package com.apress.spring6recipes.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfig {

	@Bean
	public UserDetailsManager userDetailsService() {
		var user = User.withDefaultPasswordEncoder()
						.username("user").password("user").authorities("USER").build();
		var admin = User.withDefaultPasswordEncoder()
						.username("admin").password("admin").authorities("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.formLogin(Customizer.withDefaults());
		http.authorizeHttpRequests( auth ->
							auth
								.requestMatchers(HttpMethod.DELETE, "/todos/*").hasAuthority("ADMIN")
								.requestMatchers("/todos", "/todos/*").hasAuthority("USER"));
		return http.build();
	}
}
