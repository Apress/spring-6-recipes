package com.apress.spring6recipes.court.config;

import com.apress.spring6recipes.court.domain.SportTypeConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for the Court reservation application. Enables component scannig so
 * that all services and controllers will be detected (as long as they are part of the
 * {@code com.apress.spring6recipes.court} package (or in a sub-package).
 *
 * @author Marten Deinum
 */
@Configuration
@ComponentScan("com.apress.spring6recipes.court")
@EnableWebMvc
public class CourtConfiguration implements WebMvcConfigurer {

	private final SportTypeConverter sportTypeConverter;

	public CourtConfiguration(SportTypeConverter sportTypeConverter) {
		this.sportTypeConverter = sportTypeConverter;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(sportTypeConverter);
	}
}
