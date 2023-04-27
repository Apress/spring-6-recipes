package com.apress.spring6recipes.post;

import com.apress.spring6recipes.post.config.BackOfficeConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BackOfficeMain {

	public static void main(String[] args) {
		var cfg = BackOfficeConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {
			var backOffice = context.getBean(BackOffice.class);
			var mail = backOffice.receiveMail();
			System.out.printf("Recevied: %s%n", mail);
		}
	}
}
