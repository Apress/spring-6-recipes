package com.apress.spring6recipes.springintegration;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.connection.CachingConnectionFactory;

import com.apress.spring6recipes.springintegration.myholiday.VacationServiceImpl;

@Configuration
@EnableIntegration
public class ServerIntegrationContext {

	@Bean
	public CachingConnectionFactory connectionFactory() {
		var url = "tcp://localhost:61616";
		var connectionFactory = new ActiveMQConnectionFactory(url);
		return new CachingConnectionFactory(connectionFactory);
	}

	@Bean
	public VacationServiceImpl vacationService() {
		return new VacationServiceImpl();
	}

	@Bean
	public IntegrationFlow serverIntegrationFlow() {
		return IntegrationFlow.from(
						Jms.inboundGateway(connectionFactory())
										.requestDestination("inboundHotelReservationSearchDestination"))
						.handle(vacationService()).get();
	}
}
