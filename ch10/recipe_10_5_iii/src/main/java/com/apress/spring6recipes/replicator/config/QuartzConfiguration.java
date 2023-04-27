package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.FileReplicationJob;
import com.apress.spring6recipes.replicator.FileReplicator;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.Collections;

@Configuration
public class QuartzConfiguration {

	@Bean
	public JobDetailFactoryBean documentReplicationJob(FileReplicator fileReplicator) {
		var documentReplicationJob = new JobDetailFactoryBean();
		documentReplicationJob.setJobClass(FileReplicationJob.class);
		documentReplicationJob.setDurability(true);
		documentReplicationJob.setJobDataAsMap(Collections.singletonMap("fileReplicator", fileReplicator));
		return documentReplicationJob;
	}

	@Bean
	public SimpleTriggerFactoryBean documentReplicationTrigger(JobDetail documentReplicationJob) {
		var documentReplicationTrigger = new SimpleTriggerFactoryBean();
		documentReplicationTrigger.setJobDetail(documentReplicationJob);
		documentReplicationTrigger.setStartDelay(5000);
		documentReplicationTrigger.setRepeatInterval(60000);
		return documentReplicationTrigger;
	}

	@Bean
	public SchedulerFactoryBean scheduler(Trigger[] triggers) {
		var scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(triggers);
		return scheduler;
	}
}
