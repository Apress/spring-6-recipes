package com.apress.spring6recipes.sequence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		try (var ctx = new AnnotationConfigApplicationContext()) {
			ctx.registerBean(Sequence.class, () -> new Sequence("30", "A", 10000));
			ctx.refresh();
			var generator = ctx.getBean(Sequence.class);
			System.out.println(generator.nextValue());
			System.out.println(generator.nextValue());
		}
	}
}