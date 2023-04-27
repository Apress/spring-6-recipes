package com.apress.spring6recipes.weather.config;

import com.apress.spring6recipes.weather.WeatherService;
import com.apress.spring6recipes.weather.WeatherServiceClient;
import com.apress.spring6recipes.weather.WeatherServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SpringWsClientConfiguration {

	@Bean
	public WeatherServiceClient weatherServiceClient(WeatherService weatherService) {
		return new WeatherServiceClient(weatherService);
	}

	@Bean
	public WeatherServiceProxy weatherServiceProxy(WebServiceTemplate wst) {
		return new WeatherServiceProxy(wst);
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		var webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setDefaultUri("http://localhost:8080/springws/services");
		return webServiceTemplate;
	}
}
