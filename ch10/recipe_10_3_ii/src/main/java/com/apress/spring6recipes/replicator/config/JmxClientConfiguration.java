package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.FileReplicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.access.MBeanProxyFactoryBean;

@Configuration
public class JmxClientConfiguration {

	@Bean
	public MBeanProxyFactoryBean fileReplicatorProxy() throws Exception {
		var url = "service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator";
		var name = "bean:name=documentReplicator,type=JMXFileReplicator";
		var fileReplicatorProxy = new MBeanProxyFactoryBean();
		fileReplicatorProxy.setServiceUrl(url);
		fileReplicatorProxy.setObjectName(name);
		fileReplicatorProxy.setProxyInterface(FileReplicator.class);
		return fileReplicatorProxy;
	}
}
