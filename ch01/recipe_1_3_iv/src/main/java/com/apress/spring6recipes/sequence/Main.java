package com.apress.spring6recipes.sequence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.sequence.config.SequenceConfiguration;

public class Main {

	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(SequenceConfiguration.class)) {

			var generator = context.getBean(Sequence.class);

			System.out.println(generator.nextValue());
			System.out.println(generator.nextValue());
		}
	}

}
