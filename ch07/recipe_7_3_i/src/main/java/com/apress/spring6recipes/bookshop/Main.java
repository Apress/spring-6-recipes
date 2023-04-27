package com.apress.spring6recipes.bookshop;

import com.apress.spring6recipes.bookshop.config.BookstoreConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) throws Throwable {
		var cfg = BookstoreConfiguration.class;
		try (var  context = new AnnotationConfigApplicationContext(cfg)) {
			var bookShop = context.getBean(BookShop.class);
			bookShop.purchase("0001", "user1");
		}
	}
}
