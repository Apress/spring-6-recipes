package com.apress.spring6recipes.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import com.apress.spring6recipes.shop.BannerLoader;
import com.apress.spring6recipes.shop.Battery;
import com.apress.spring6recipes.shop.Disc;
import com.apress.spring6recipes.shop.Product;

@Configuration
@PropertySource("classpath:discounts.properties")
@ComponentScan("com.apress.spring6recipes.shop")
public class ShopConfiguration {

	@Value("${specialcustomer.discount:0}")
	private double specialCustomerDiscountField;

	@Value("${summer.discount:0}")
	private double specialSummerDiscountField;

	@Value("${endofyear.discount:0}")
	private double specialEndofyearDiscountField;

	@Value("classpath:banner.txt")
	private Resource banner;

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public BannerLoader bannerLoader() {
		return new BannerLoader(banner);
	}

	@Bean
	public Product aaa() {
		return new Battery("AAA", 2.5, true, specialCustomerDiscountField);
	}

	@Bean
	public Product cdrw() {
		return new Disc("CD-RW", 1.5, 700, specialSummerDiscountField);
	}

	@Bean
	public Product dvdrw() {
		return new Disc("DVD-RW", 1.5, 4700, specialEndofyearDiscountField);
	}
}
