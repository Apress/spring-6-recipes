package com.apress.spring6recipes.springbatch;

import com.apress.spring6recipes.springbatch.config.BatchConfiguration;
import com.apress.spring6recipes.utils.Utils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {
	public static void main(String[] args) throws Throwable {

		var cfg = BatchConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {

			var jobLauncher = context.getBean(JobLauncher.class);
			var job = context.getBean(Job.class);

			var jobParametersBuilder = new JobParametersBuilder();
			jobParametersBuilder.addDate("date", new Date());
			JobParameters jobParameters = jobParametersBuilder.toJobParameters();

			var jobExecution = jobLauncher.run(job, jobParameters);
			var batchStatus = jobExecution.getStatus();

			while (batchStatus.isRunning()) {
				System.out.println("Still running...");
				Utils.sleep(500);
			}
			System.out.println("Exit status: " + jobExecution.getExitStatus().getExitCode());

			var jobInstance = jobExecution.getJobInstance();
			System.out.println("job instance Id: " + jobInstance.getId());
		}
	}
}
