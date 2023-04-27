package com.apress.spring6recipes.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

@Configuration
@EnableWs
@ComponentScan("com.apress.spring6recipes.weather")
public class SpringWsConfiguration extends WsConfigurerAdapter {

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

	@Bean
	public Jaxb2Marshaller marshaller() {
		var marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.apress.spring6recipes.weather.schemas");
		return marshaller;
	}

	@Override
	public void addArgumentResolvers(List<MethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new MarshallingPayloadMethodProcessor(marshaller()));
	}

	@Override
	public void addReturnValueHandlers(List<MethodReturnValueHandler> returnValueHandlers) {
		returnValueHandlers.add(new MarshallingPayloadMethodProcessor(marshaller()));
	}
}
