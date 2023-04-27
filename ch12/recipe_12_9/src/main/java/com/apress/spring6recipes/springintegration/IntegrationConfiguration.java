package com.apress.spring6recipes.springintegration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.integration.launch.JobLaunchingMessageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;

import java.io.File;
import java.time.Duration;

public class IntegrationConfiguration {

	@Bean
	public FileToJobLaunchRequestTransformer transformer(Job job) {
		return new FileToJobLaunchRequestTransformer(job, "filename");
	}

	@Bean
	public JobLaunchingMessageHandler jobLaunchingMessageHandler(JobLauncher launcher) {
		return new JobLaunchingMessageHandler(launcher);
	}

	@Bean
	public IntegrationFlow fileToBatchFlow(
						@Value("file:${user.home}/customerstoimport/new/") File directory,
				    FileToJobLaunchRequestTransformer transformer,
				    JobLaunchingMessageHandler handler) {
    return IntegrationFlow
                .from(
												Files.inboundAdapter(directory)
																.patternFilter("customers-*.txt"),
												c -> c.poller(Pollers.fixedRate(Duration.ofSeconds(1))))
                .transform(transformer)
                .handle(handler)
            .get();
    }
}
