package com.apress.spring6recipes.springbatch;

import com.apress.spring6recipes.springbatch.config.BatchConfiguration;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		var cfg = BatchConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {

			var jobRegistry = context.getBean(JobRegistry.class);
			var jobLauncher = context.getBean(JobLauncher.class);
			var jobRepository = context.getBean(JobRepository.class);

			System.out.println("JobRegistry: " + jobRegistry);
			System.out.println("JobLauncher: " + jobLauncher);
			System.out.println("JobRepository: " + jobRepository);
		}
	}
}
