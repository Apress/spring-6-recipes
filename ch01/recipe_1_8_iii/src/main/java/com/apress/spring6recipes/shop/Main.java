package com.apress.spring6recipes.shop;

import com.apress.spring6recipes.shop.config.ShopConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		try (var context = new AnnotationConfigApplicationContext(ShopConfiguration.class)) {

			var aaa = context.getBean("aaa", Product.class);
			var cdrw = context.getBean("cdrw", Product.class);
			var dvdrw = context.getBean("dvdrw", Product.class);

			var cartProvider = context.getBeanProvider(ShoppingCart.class);
			var cart1 = cartProvider.getIfAvailable();
			cart1.addItem(aaa);
			cart1.addItem(cdrw);
			System.out.println("Shopping cart 1 contains " + cart1.getItems());

			var cart2 = cartProvider.getIfAvailable();
			cart2.addItem(dvdrw);
			System.out.println("Shopping cart 2 contains " + cart2.getItems());

			var cashier = context.getBean(Cashier.class);
			cashier.checkout(cart1);
			cashier.checkout(cart2);
		}

	}

}
