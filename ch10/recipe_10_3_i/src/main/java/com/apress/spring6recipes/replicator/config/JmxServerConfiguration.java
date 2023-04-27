package com.apress.spring6recipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.ConnectorServerFactoryBean;

@Configuration
@Import(FileReplicatorConfig.class)
public class JmxServerConfiguration {

	@Bean
	public ConnectorServerFactoryBean connectorServerFactoryBean() {
		var url ="service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator";
		var connectorServer = new ConnectorServerFactoryBean();
		connectorServer.setServiceUrl(url);
		return connectorServer;
	}
}
