package com.apress.spring6recipes.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout-success").setViewName("logout-success");
	}

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		var url = "ldap://ldap-server:389/dc=spring6recipes,dc=com";
		return new DefaultSpringSecurityContextSource(url);
	}

	@Bean
	public AuthenticationManager authenticationManager(
					DefaultSpringSecurityContextSource contextSource) {
		var populator = new DefaultLdapAuthoritiesPopulator(contextSource, "ou=groups");
		populator.setRolePrefix("");

		var factory = new LdapBindAuthenticationManagerFactory(contextSource);
		factory.setUserDnPatterns("uid={0},ou=people");
		factory.setLdapAuthoritiesPopulator(populator);
		return factory.createAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.httpBasic().disable();
		http.formLogin()
						.loginPage("/login").permitAll()
						.defaultSuccessUrl("/todos")
						.failureUrl("/login?error=true");
		http.logout().logoutSuccessUrl("/logout-success").permitAll();
		http.authorizeHttpRequests(auth ->
						auth
										.requestMatchers(HttpMethod.DELETE, "/todos/*").hasAuthority("ADMIN")
										.requestMatchers("/todos", "/todos/*").hasAuthority("USER"));
		return http.build();
	}
}
