package com.apress.spring6recipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.apress.spring6recipes.shop.Cashier;

@Configuration
@Profile("global")
@ComponentScan("com.apress.spring6recipes.shop")
public class ShopConfigurationGlobal {

	@Bean(initMethod = "openFile", destroyMethod = "closeFile")
	public Cashier cashier() {
		var path = System.getProperty("java.io.tmpdir") + "cashier";
		return new Cashier(path);
	}
}
