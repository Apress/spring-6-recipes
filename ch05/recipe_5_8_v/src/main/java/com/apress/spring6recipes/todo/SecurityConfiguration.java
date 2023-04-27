package com.apress.spring6recipes.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

	private final TodoService todoService;

	public SecurityConfiguration(TodoService todoService) {
		this.todoService = todoService;
	}

	@Bean
	public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
		return http
			.formLogin( (formLogin) -> formLogin.loginPage("/login"))
			.csrf( (csrf) -> csrf.csrfTokenRepository(new CookieServerCsrfTokenRepository()))
			.authorizeExchange( (auth) -> {
					auth.pathMatchers("/login").permitAll();
					auth.pathMatchers("/todos").hasAuthority("USER");
					auth.pathMatchers(HttpMethod.DELETE, "/todos").access(this::todoRemoveAllowed);
				}
			)
			.build();
	}

	private Mono<AuthorizationDecision> todoRemoveAllowed(Mono<Authentication> authentication, AuthorizationContext context) {
		return authentication
						.map ( (auth) -> auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")) || isOwner(auth, context))
						.map(AuthorizationDecision::new);
	}

	private boolean isOwner(Authentication auth, AuthorizationContext context) {
		var id = Long.valueOf(context.getVariables().getOrDefault("id", "-1").toString());
		return todoService.findById(id)
						.map( (todo) -> Objects.equals(todo.getOwner(), auth.getName())).defaultIfEmpty(false).block();
	}

	@Bean
	public MapReactiveUserDetailsService userDetailsRepository() {
		var marten = User.withDefaultPasswordEncoder().username("marten").password("secret").authorities("USER").build();
		var admin = User.withDefaultPasswordEncoder().username("admin").password("admin").authorities("USER", "ADMIN").build();
		return new MapReactiveUserDetailsService(marten, admin);
	}
}
