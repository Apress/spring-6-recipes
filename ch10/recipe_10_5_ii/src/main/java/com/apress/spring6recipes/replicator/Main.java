package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.FileReplicatorConfig;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		try (var ctx =
								 new AnnotationConfigApplicationContext(FileReplicatorConfig.class)) {

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
							.withSchedule(CronScheduleBuilder.cronSchedule("0/60 * * * * ?"))
							.build();

			var scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		}
	}
}