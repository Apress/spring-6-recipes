package com.apress.spring6recipes.weather;

import java.time.LocalDate;

public record TemperatureInfo(
				String city, LocalDate date, double min, double max, double average) {
}
