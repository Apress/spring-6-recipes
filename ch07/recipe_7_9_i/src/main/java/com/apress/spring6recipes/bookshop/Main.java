package com.apress.spring6recipes.bookshop;

import com.apress.spring6recipes.bookshop.config.BookstoreConfiguration;
import com.apress.spring6recipes.utils.Utils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(BookstoreConfiguration.class)) {

			var bookShop = context.getBean(BookShop.class);

			var thread1 = new Thread(() -> bookShop.checkStock("0001"), "Thread 1");

			var thread2 = new Thread(() -> {
				try {
					bookShop.increaseStock("0001", 5);
				} catch (RuntimeException e) {
				}
			}, "Thread 2");

			thread1.start();
			Utils.sleep(5000);
			thread2.start();

		}
	}

}
