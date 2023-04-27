package com.apress.spring6recipes.weather;

import java.time.LocalDate;
import java.util.Collections;

public class WeatherServiceClient {

	private final WeatherService weatherService;

	public WeatherServiceClient(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public TemperatureInfo getTodayTemperature(String city) {

		var dates = Collections.singletonList(LocalDate.now());
		var temperatures = weatherService.getTemperatures(city, dates);
		return temperatures.get(0);
	}
}
