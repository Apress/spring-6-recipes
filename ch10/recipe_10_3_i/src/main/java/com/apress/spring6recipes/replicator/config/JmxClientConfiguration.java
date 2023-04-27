package com.apress.spring6recipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import java.net.MalformedURLException;

@Configuration
public class JmxClientConfiguration {

	@Bean
	public MBeanServerConnectionFactoryBean mbeanServerConnection()
					throws MalformedURLException {
		var url = "service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator";
		var mBeanServerConnectionFactoryBean = new MBeanServerConnectionFactoryBean();
		mBeanServerConnectionFactoryBean.setServiceUrl(url);
		return mBeanServerConnectionFactoryBean;
	}
}
