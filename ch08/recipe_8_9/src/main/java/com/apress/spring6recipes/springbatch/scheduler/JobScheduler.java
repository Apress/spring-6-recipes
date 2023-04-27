package com.apress.spring6recipes.springbatch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JobScheduler {

	private final JobLauncher jobLauncher;
	private final Job job;

	public JobScheduler(JobLauncher jobLauncher, Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}

	public void runRegistrationsJob(LocalDateTime date) throws Throwable {
		System.out.println("Starting job at " + date.toString());

		var jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLocalDateTime("date", date);
		jobParametersBuilder.addString("input.file", "registrations");

		var jobParameters = jobParametersBuilder.toJobParameters();

		var jobExecution = jobLauncher.run(job, jobParameters);

		System.out.println("jobExecution finished, exit code: " + jobExecution.getExitStatus().getExitCode());
	}

	@Scheduled(fixedDelay = 1000 * 10)
	public void runRegistrationsJobOnASchedule() throws Throwable {
		runRegistrationsJob(new Date());
	}
}
