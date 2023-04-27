package com.apress.spring6recipes.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@ComponentScan("com.apress.spring6recipes.weather")
public class SpringWsConfiguration {

	@Bean
	public DefaultWsdl11Definition temperature() {
		var temperature = new DefaultWsdl11Definition();
		temperature.setPortTypeName("Weather");
		temperature.setLocationUri("/");
		temperature.setSchema(temperatureSchema());
		return temperature;
	}

	@Bean
	public XsdSchema temperatureSchema() {
		var xsd = new ClassPathResource("/META-INF/xsd/temperature.xsd");
		return new SimpleXsdSchema(xsd);
	}
}
