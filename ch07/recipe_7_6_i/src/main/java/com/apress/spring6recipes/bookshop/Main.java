package com.apress.spring6recipes.bookshop;

import java.util.List;

import com.apress.spring6recipes.bookshop.config.BookstoreConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		var cfg =BookstoreConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {
			var isbnList = List.of("0001", "0002");
			var cashier = context.getBean(Cashier.class);
			cashier.checkout(isbnList, "user1");
		}
	}
}
