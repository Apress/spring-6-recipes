package com.apress.spring6recipes.springintegration;

import com.apress.spring6recipes.springintegration.myholiday.VacationService;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
@EnableIntegration
public class ClientIntegrationConfig {

	@Bean
	public CachingConnectionFactory connectionFactory() {
		var connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		return new CachingConnectionFactory(connectionFactory);
	}

	@Bean
	public IntegrationFlow vacationGatewayFlow() {
			return IntegrationFlow
							.from(VacationService.class)
							.handle(
									Jms.outboundGateway(connectionFactory())
												.requestDestination("inboundHotelReservationSearchDestination")
												.replyDestination("outboundHotelReservationSearchResultsDestination"))
								.get();
		}
}
