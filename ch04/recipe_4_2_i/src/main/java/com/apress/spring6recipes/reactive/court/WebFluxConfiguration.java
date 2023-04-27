package com.apress.spring6recipes.reactive.court;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
@ComponentScan
public class WebFluxConfiguration implements WebFluxConfigurer {

	@Override
	public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
		var mapper = Jackson2ObjectMapperBuilder.json()
						.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).build();
		var jackson = new Jackson2JsonEncoder(mapper);

		configurer.defaultCodecs().jackson2JsonEncoder(jackson);
	}
}
