package com.apress.spring6recipes.replicator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		var cfg = "com.apress.spring6recipes.replicator.config";
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
			System.in.read();
		}
	}
}
