package com.apress.spring6recipes.springbatch;

import com.apress.spring6recipes.springbatch.config.BatchConfiguration;
import com.apress.spring6recipes.springbatch.config.UserJob;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) throws Throwable {
		var cfg = new Class[] {BatchConfiguration.class, UserJob.class};
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {}
	}
}
