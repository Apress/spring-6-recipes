package com.apress.spring6recipes.court.config;

import com.apress.spring6recipes.court.service.ReservationNotAvailableException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.List;
import java.util.Properties;

@Configuration
public class ErrorHandlingConfiguration implements WebMvcConfigurer {

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(handlerExceptionResolver());
	}

	@Bean
	public HandlerExceptionResolver handlerExceptionResolver() {
		var mappings = new Properties();
		mappings.setProperty(ReservationNotAvailableException.class.getName(), "reservationNotAvailable");

		var resolver = new SimpleMappingExceptionResolver();
		resolver.setExceptionMappings(mappings);
		resolver.setDefaultErrorView("error");
		return resolver;
	}
}
