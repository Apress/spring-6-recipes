package com.apress.spring6recipes.sequence;

import com.apress.spring6recipes.sequence.config.SequenceConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		try (var context = new AnnotationConfigApplicationContext(SequenceConfiguration.class)) {

			var generator = context.getBean(Sequence.class);

			System.out.println(generator.getSequence());
			System.out.println(generator.getSequence());
		}
	}

}
