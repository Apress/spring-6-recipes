package com.apress.spring6recipes.weather;

import java.time.LocalDate;
import java.util.List;

public interface WeatherService {

	List<TemperatureInfo> getTemperatures(String city, List<LocalDate> dates);
}
