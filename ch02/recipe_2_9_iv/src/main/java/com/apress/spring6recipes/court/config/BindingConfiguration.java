package com.apress.spring6recipes.court.config;

import com.apress.spring6recipes.court.domain.SportTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by marten on 07-03-17.
 */
@Configuration
public class BindingConfiguration implements WebMvcConfigurer {

	private final SportTypeConverter sportTypeConverter;

	public BindingConfiguration(SportTypeConverter sportTypeConverter) {
		this.sportTypeConverter = sportTypeConverter;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(this.sportTypeConverter);
	}

}
