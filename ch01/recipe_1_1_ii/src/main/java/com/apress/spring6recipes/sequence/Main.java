package com.apress.spring6recipes.sequence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		var basePackages = "com.apress.spring6recipes.sequence";
		try (var context = new AnnotationConfigApplicationContext(basePackages)) {

			var sequenceDao = context.getBean(SequenceDao.class);
			System.out.println(sequenceDao.getNextValue("IT"));
			System.out.println(sequenceDao.getNextValue("IT"));
		}
	}
}
