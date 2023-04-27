package com.apress.spring6recipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.apress.spring6recipes.shop.Battery;
import com.apress.spring6recipes.shop.DiscountFactoryBean;
import com.apress.spring6recipes.shop.Disc;

@Configuration
@ComponentScan("com.apress.spring6recipes.shop")
public class ShopConfiguration {

	@Bean
	public Battery aaa() {
		return new Battery("AAA", 2.5, true);
	}

	@Bean
	public Disc cdrw() {
		return new Disc("CD-RW", 1.5, 700);
	}

	@Bean
	public Disc dvdrw() {
		return new Disc("DVD-RW", 3.0, 4700);
	}

	@Bean
	public DiscountFactoryBean discountFactoryBeanAAA(Battery aaa) {
		var factory = new DiscountFactoryBean();
		factory.setProduct(aaa);
		factory.setDiscount(0.2);
		return factory;
	}

	@Bean
	public DiscountFactoryBean discountFactoryBeanCDRW(Disc cdrw) {
		var factory = new DiscountFactoryBean();
		factory.setProduct(cdrw);
		factory.setDiscount(0.1);
		return factory;
	}

	@Bean
	public DiscountFactoryBean discountFactoryBeanDVDRW(Disc dvdrw) {
		var factory = new DiscountFactoryBean();
		factory.setProduct(dvdrw);
		factory.setDiscount(0.1);
		return factory;
	}
}
