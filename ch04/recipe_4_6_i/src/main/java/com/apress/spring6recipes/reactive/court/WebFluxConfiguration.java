package com.apress.spring6recipes.reactive.court;

import com.apress.spring6recipes.reactive.court.web.MemberController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
@ComponentScan
public class WebFluxConfiguration implements WebFluxConfigurer {

	@Bean
	public RouterFunction<ServerResponse> membersRouter(MemberController handler) {
		return RouterFunctions.route()
						.GET("/members", handler::list)
						.POST("/members", handler::create)
					.build();
	}
}
