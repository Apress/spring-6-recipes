package com.apress.spring6recipes.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.shop.config.ShopConfiguration;

public class Main {

	public static void main(String[] args) throws Exception {

		try (var context = new AnnotationConfigApplicationContext(ShopConfiguration.class)) {

			var aaa = context.getBean("aaa", Product.class);
			var cdrw = context.getBean("cdrw", Product.class);
			var dvdrw = context.getBean("dvdrw", Product.class);

			var cart1 = context.getBean(ShoppingCart.class);
			cart1.addItem(aaa);
			cart1.addItem(cdrw);
			System.out.println("Shopping cart 1 contains " + cart1.getItems());

			var cart2 = context.getBean(ShoppingCart.class);
			cart2.addItem(dvdrw);
			System.out.println("Shopping cart 2 contains " + cart2.getItems());
		}
	}

}
