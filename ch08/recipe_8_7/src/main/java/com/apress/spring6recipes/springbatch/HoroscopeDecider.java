package com.apress.spring6recipes.springbatch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import java.util.concurrent.ThreadLocalRandom;

public class HoroscopeDecider implements JobExecutionDecider {

	private boolean isMercuryIsInRetrograde() {
		return ThreadLocalRandom.current().nextDouble() > .9;
	}

	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		if (isMercuryIsInRetrograde()) {
			return FlowExecutionStatus.FAILED;
		}
		return FlowExecutionStatus.COMPLETED;
	}
}
