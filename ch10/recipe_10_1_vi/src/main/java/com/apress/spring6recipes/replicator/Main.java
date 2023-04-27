package com.apress.spring6recipes.replicator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		try (var ctx = new AnnotationConfigApplicationContext("com.apress.spring6recipes.replicator.config")) {
			System.in.read();
		}

	}
}
