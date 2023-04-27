package com.apress.spring6recipes.bookshop;

import java.util.List;

import com.apress.spring6recipes.bookshop.config.BookstoreConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * TO RUN:
 *
 * You need the Spring AspectJ load time weaver, spring-instrument-6.0.x.jar. If you built
 * the source tree with Gradle, there will be a build/lib/ folder in your 'transactions'
 * folder.
 *
 * Thus, you can add this to your invocation of the java command:
 *
 * -javaagent:build/lib/spring-instrument-6.0.x.jar
 *
 */
public class Main {

	public static void main(String[] args) {

		try (var context = new AnnotationConfigApplicationContext(BookstoreConfiguration.class)) {

			var isbnList = List.of("0001", "0002");
			var cashier = context.getBean(Cashier.class);
			cashier.checkout(isbnList, "user1");
		}
	}

}
