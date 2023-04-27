package com.apress.spring6recipes.court.domain;

import com.apress.spring6recipes.court.service.ReservationService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SportTypeConverter implements Converter<String, SportType> {

	private final ReservationService reservationService;

	public SportTypeConverter(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@Override
	public SportType convert(String source) {
		var sportTypeId = Integer.parseInt(source);
		return reservationService.getSportType(sportTypeId);
	}
}
