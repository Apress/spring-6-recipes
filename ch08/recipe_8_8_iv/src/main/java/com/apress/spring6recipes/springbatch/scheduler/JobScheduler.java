package com.apress.spring6recipes.springbatch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JobScheduler {

	private final JobLauncher jobLauncher;
	private final Job job;

	public JobScheduler(JobLauncher jobLauncher, Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}

	public void runRegistrationsJob(LocalDateTime date) throws Exception{
		System.out.println("Starting job at " + date.toString());

		var jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLocalDateTime("date", date);
		jobParametersBuilder.addString("input.file", "registrations");

		var jobParameters = jobParametersBuilder.toJobParameters();
		var jobExecution = jobLauncher.run(job, jobParameters);
		var exitcode = jobExecution.getExitStatus().getExitCode();
		System.out.printf("jobExecution finished, exit code: %s%n", exitcode);
	}

	@Scheduled(fixedDelay = 10_0000)
	public void runRegistrationsJobOnASchedule() throws Exception {
		runRegistrationsJob(LocalDateTime.now());
	}
}
