package com.apress.spring6recipes.sequence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		try (var context = new GenericXmlApplicationContext("appContext.xml")) {

			var generator = context.getBean("sequenceGenerator", Sequence.class);

			System.out.println(generator.getSequence());
			System.out.println(generator.getSequence());
		}
	}

}
