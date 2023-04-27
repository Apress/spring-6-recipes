package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.MailConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

	public static void main(String[] args) {
		try (var ctx =
								 new AnnotationConfigApplicationContext(MailConfiguration.class)) {

			var errorNotifier = ctx.getBean(ErrorNotifier.class);
			errorNotifier.notifyCopyError("c:/documents", "d:/documents", "spring.doc");
		}
	}
}