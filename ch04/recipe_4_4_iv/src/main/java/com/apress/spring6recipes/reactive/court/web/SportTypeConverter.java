package com.apress.spring6recipes.reactive.court.web;

import com.apress.spring6recipes.reactive.court.domain.SportType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SportTypeConverter implements Converter<String, SportType> {

	private final SportTypeRepository repository;

	public SportTypeConverter(SportTypeRepository repository) {
		this.repository = repository;
	}

	@Override
	public SportType convert(String source) {
		var sportTypeId = Integer.parseInt(source);
		return repository.findById(sportTypeId).orElse(null);
	}
}
