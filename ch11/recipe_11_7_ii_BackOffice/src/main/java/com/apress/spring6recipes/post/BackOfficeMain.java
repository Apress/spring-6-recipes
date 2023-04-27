package com.apress.spring6recipes.post;

import com.apress.spring6recipes.post.config.BackOfficeConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BackOfficeMain {

    public static void main(String[] args) throws Exception {
			var cfg = BackOfficeConfiguration.class;
			try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
				System.in.read();
			}
    }
}
