package com.apress.spring6recipes.sequence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		try (var context =
							new AnnotationConfigApplicationContext("com.apress.springrecipes.sequence")) {

			var sequenceService = context.getBean(SequenceService.class);
			System.out.println(sequenceService.generate("IT"));
			System.out.println(sequenceService.generate("IT"));
		}
	}
}
