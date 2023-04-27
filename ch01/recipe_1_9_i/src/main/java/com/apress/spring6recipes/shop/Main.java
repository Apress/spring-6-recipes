package com.apress.spring6recipes.shop;

import com.apress.spring6recipes.shop.config.ShopConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		var cfg = ShopConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {

			var aaa = (Product) context.getBean("aaa");
			var cdrw = (Product) context.getBean("cdrw");
			var dvdrw = (Product) context.getBean("dvdrw");

			var cart1 = context.getBean(ShoppingCart.class);
			cart1.addItem(aaa);
			cart1.addItem(cdrw);
			System.out.println("Shopping cart 1 contains " + cart1.getItems());

			var cart2 = context.getBean(ShoppingCart.class);
			cart2.addItem(dvdrw);
			System.out.println("Shopping cart 2 contains " + cart2.getItems());

			var cashier = context.getBean(Cashier.class);
			cashier.checkout(cart1);
			cashier.checkout(cart2);
		}
	}
}
