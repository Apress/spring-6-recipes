package com.apress.spring6recipes.shop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.apress.spring6recipes.shop.config.ShopConfiguration;

public class Main {

	public static void main(String[] args) throws Exception {
		try (var context = new AnnotationConfigApplicationContext(ShopConfiguration.class)) {

			var aaa = context.getBean("aaa", Product.class);
			var cdrw = context.getBean("cdrw", Product.class);
			var dvdrw = context.getBean("dvdrw", Product.class);

			var cart1 = context.getBean("shoppingCart", ShoppingCart.class);
			cart1.addItem(aaa);
			cart1.addItem(cdrw);
			System.out.println("Shopping cart 1 contains " + cart1.getItems());

			var cart2 = context.getBean("shoppingCart", ShoppingCart.class);
			cart2.addItem(dvdrw);
			System.out.println("Shopping cart 2 contains " + cart2.getItems());
		}

		var resource = new ClassPathResource("discounts.properties");
		var props = PropertiesLoaderUtils.loadProperties(resource);
		System.out.println("And don't forget our discounts!");
		System.out.println(props);
	}
}
