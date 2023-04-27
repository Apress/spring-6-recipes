package com.apress.spring6recipes.springbatch;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.springbatch.config.BatchConfiguration;

public class Main {
	public static void main(String[] args) throws Throwable {
		var cfg = BatchConfiguration.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
			var jobLauncher = ctx.getBean(JobLauncher.class);
			var job = ctx.getBean("dailySalesFigures", Job.class);
			var builder = new JobParametersBuilder();
			var parameters = builder.addLocalDateTime("date", LocalDateTime.now())
							.toJobParameters();
			jobLauncher.run(job, parameters);
	}}
}
