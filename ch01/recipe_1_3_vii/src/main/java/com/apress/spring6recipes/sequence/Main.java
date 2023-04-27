package com.apress.spring6recipes.sequence;

import com.apress.spring6recipes.sequence.config.SequenceConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		var cfg = SequenceConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {
			var generator = context.getBean("sequence", Sequence.class);
			System.out.println(generator.nextValue());
			System.out.println(generator.nextValue());
		}
	}
}
