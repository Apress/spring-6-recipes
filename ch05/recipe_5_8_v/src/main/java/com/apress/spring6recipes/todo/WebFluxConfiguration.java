package com.apress.spring6recipes.todo;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.web.reactive.result.view.CsrfRequestDataValueProcessor;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.thymeleaf.spring6.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring6.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFlux
@EnableTransactionManagement
@ComponentScan
public class WebFluxConfiguration implements WebFluxConfigurer{

	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
		var resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}

	@Bean
	public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
		var templateEngine = new SpringWebFluxTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
		return templateEngine;
	}

	@Bean
	public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver() {
		var viewResolver = new ThymeleafReactiveViewResolver();
		viewResolver.setTemplateEngine(thymeleafTemplateEngine());
		viewResolver.setResponseMaxChunkSizeBytes(16384);
		return viewResolver;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(thymeleafReactiveViewResolver());
	}

	@Bean
	public RouterFunction<ServerResponse> securityPages() {
		return RouterFunctions
						.route().filter(csrfToken())
						.GET("/login", (req) -> ServerResponse.ok().render("login")).build();
	}

	public HandlerFilterFunction<ServerResponse, ServerResponse> csrfToken() {
		var name = CsrfToken.class.getName();
		return (req, next) -> req.exchange()
						.getAttributeOrDefault(name, Mono.empty().ofType(CsrfToken.class))
						.flatMap(token -> {
							req.exchange()
									.getAttributes()
									.put(CsrfRequestDataValueProcessor.DEFAULT_CSRF_ATTR_NAME, token);
							return next.handle(req);
						});
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		return ConnectionFactories.get("r2dbc:h2:mem:///todos?options=DB_CLOSE_DELAY=-1;");
	}

	@Bean
	public ConnectionFactoryInitializer connectionFactoryInitializer(ConnectionFactory connectionFactory) {
		var populator = new ResourceDatabasePopulator();
		populator.setScripts(new ClassPathResource("schema.sql"),
						             new ClassPathResource("data.sql"));

		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(populator);
		return initializer;
	}

	@Bean
	public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
		return DatabaseClient.create(connectionFactory);
	}

	@Bean
	public R2dbcTransactionManager transactionManager(ConnectionFactory connectionFactory) {
		return new R2dbcTransactionManager(connectionFactory);
	}
}
