package com.apress.spring6recipes.replicator;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;

public class FileReplicationJob extends QuartzJobBean {

	private FileReplicator fileReplicator;

	public void setFileReplicator(FileReplicator fileReplicator) {
		this.fileReplicator = fileReplicator;
	}

	protected void executeInternal(JobExecutionContext context)
					throws JobExecutionException {
		try {
			fileReplicator.replicate();
		} catch (IOException e) {
			throw new JobExecutionException(e);
		}
	}
}
