package com.apress.spring6recipes.weather;

import com.apress.spring6recipes.weather.config.SpringWsClientConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringWSInvokerClient {

	public static void main(String[] args) {
		var cfg = SpringWsClientConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {

			var client = context.getBean(WeatherServiceClient.class);
			var temperature = client.getTodayTemperature("Houston");
			System.out.println("Min temperature : " + temperature.min());
			System.out.println("Max temperature : " + temperature.max());
			System.out.println("Average temperature : " + temperature.average());
		}
	}
}
