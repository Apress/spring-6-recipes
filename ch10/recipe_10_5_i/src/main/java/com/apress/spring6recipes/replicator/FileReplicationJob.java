package com.apress.spring6recipes.replicator;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;

public class FileReplicationJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
					throws JobExecutionException {
		var dataMap = context.getJobDetail().getJobDataMap();
		var fileReplicator = (FileReplicator) dataMap.get("fileReplicator");
		try {
			fileReplicator.replicate();
		} catch (IOException e) {
			throw new JobExecutionException(e);
		}
	}
}