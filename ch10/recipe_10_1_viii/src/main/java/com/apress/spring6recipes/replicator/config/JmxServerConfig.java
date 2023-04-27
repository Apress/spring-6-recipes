package com.apress.spring6recipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.ConnectorServerFactoryBean;

@Configuration
public class JmxServerConfig {

	@Bean
	public ConnectorServerFactoryBean connectorServerFactoryBean() throws Exception {
		var connectorServer = new ConnectorServerFactoryBean();
		connectorServer.setObjectName("connector:name=rmi");
		connectorServer.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator");
		return connectorServer;
	}
}
