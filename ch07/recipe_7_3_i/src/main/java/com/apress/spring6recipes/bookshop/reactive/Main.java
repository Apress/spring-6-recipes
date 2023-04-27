package com.apress.spring6recipes.bookshop.reactive;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.bookshop.config.BookstoreConfiguration;

public class Main {

	public static void main(String[] args) throws Throwable {
		var cfg = BookstoreConfiguration.class;
		try (var  context = new AnnotationConfigApplicationContext(cfg)) {
			var bookShop = context.getBean(BookShop.class);
			var counter = new CountDownLatch(1);
			bookShop.purchase("0001", "user1").subscribe( (x) -> counter.countDown());
			counter.await();
		}
	}
}
