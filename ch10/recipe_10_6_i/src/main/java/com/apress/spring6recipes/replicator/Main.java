package com.apress.spring6recipes.replicator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		try (var ctx =
								 new AnnotationConfigApplicationContext("com.apress.spring6recipes.replicator.config")) {
			System.in.read();
		}
	}
}
