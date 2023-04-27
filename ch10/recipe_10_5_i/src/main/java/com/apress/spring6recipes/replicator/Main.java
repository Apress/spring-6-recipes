package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.FileReplicatorConfig;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		var cfg = FileReplicatorConfig.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {

			var documentReplicator = ctx.getBean(FileReplicator.class);

			var jobDataMap = new JobDataMap();
			jobDataMap.put("fileReplicator", documentReplicator);

			var job = JobBuilder.newJob(FileReplicationJob.class)
							.withIdentity("documentReplicationJob")
							.storeDurably()
							.usingJobData(jobDataMap)
							.build();

			var trigger = TriggerBuilder.newTrigger()
							.withIdentity("documentReplicationTrigger")
							.startAt(new Date(System.currentTimeMillis() + 5000))
							.forJob(job)
							.withSchedule(SimpleScheduleBuilder.simpleSchedule()
											.withIntervalInSeconds(60)
											.repeatForever())
							.build();

			var scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		}
	}
}