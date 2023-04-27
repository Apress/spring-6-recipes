package com.apress.spring6recipes.springbatch;

import com.apress.spring6recipes.utils.Utils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Main {
	public static void main(String[] args) throws Throwable {
		try (var context = new ClassPathXmlApplicationContext("batch.xml", "user-job.xml")) {

			var jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
			var job = context.getBean("insertIntoDbFromCsvJob", Job.class);
			var jobParametersBuilder = new JobParametersBuilder();
			jobParametersBuilder.addDate("date", new Date());
			var jobParameters = jobParametersBuilder.toJobParameters();
			var jobExecution = jobLauncher.run(job, jobParameters);
			var batchStatus = jobExecution.getStatus();

			while (batchStatus.isRunning()) {
				System.out.println("Still running...");
				Utils.sleep(1000);
			}
			System.out.println("Exit status: " + jobExecution.getExitStatus().getExitCode());

			var jobInstance = jobExecution.getJobInstance();
			System.out.println("job instance Id: " + jobInstance.getId());

		}
	}
}
