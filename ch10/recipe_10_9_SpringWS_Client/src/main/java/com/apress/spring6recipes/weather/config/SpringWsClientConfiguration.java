package com.apress.spring6recipes.weather.config;

import com.apress.spring6recipes.weather.WeatherService;
import com.apress.spring6recipes.weather.WeatherServiceClient;
import com.apress.spring6recipes.weather.WeatherServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SpringWsClientConfiguration {

	@Bean
	public WeatherServiceClient weatherServiceClient(WeatherService weatherService) {
		return new WeatherServiceClient(weatherService);
	}

	@Bean
	public WeatherServiceProxy weatherServiceProxy(WebServiceTemplate webServiceTemplate)
					throws Exception {
		return new WeatherServiceProxy(webServiceTemplate);
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		var marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.apress.spring6recipes.weather.schemas");
		return marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate(Marshaller marshaller) {
		var webServiceTemplate = new WebServiceTemplate(marshaller);
		webServiceTemplate.setDefaultUri("http://localhost:8080/springws/services");
		return webServiceTemplate;
	}
}
