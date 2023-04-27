package com.apress.spring6recipes.sequence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		try (var ctx = new AnnotationConfigApplicationContext()) {
			ctx.registerBean(PrefixGenerator.class, () ->
							new DatePrefixGenerator("yyyyMMdd"));
			ctx.registerBean(Sequence.class, () -> {
				var seq = new Sequence("A", 100000);
				ctx.getBeanProvider(PrefixGenerator.class)
								.ifUnique(seq::setPrefixGenerator);
				return seq;
			});
			ctx.refresh();

			var generator = ctx.getBean(Sequence.class);
			System.out.println(generator.getSequence());
			System.out.println(generator.getSequence());
		}
	}
}


