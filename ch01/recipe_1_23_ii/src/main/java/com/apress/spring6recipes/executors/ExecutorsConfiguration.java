package com.apress.spring6recipes.executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;

@Configuration
@ComponentScan
public class ExecutorsConfiguration {

	@Bean
	public TaskExecutorAdapter taskExecutorAdapter() {
		return new TaskExecutorAdapter(Executors.newCachedThreadPool());
	}

	@Bean
	public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}

	@Bean
	public SyncTaskExecutor syncTaskExecutor() {
		return new SyncTaskExecutor();
	}

	@Bean
	public ScheduledExecutorFactoryBean scheduledExecutorFactoryBean(ScheduledExecutorTask scheduledExecutorTask) {
		var scheduledExecutorFactoryBean = new ScheduledExecutorFactoryBean();
		scheduledExecutorFactoryBean.setScheduledExecutorTasks(scheduledExecutorTask);
		return scheduledExecutorFactoryBean;
	}

	@Bean
	public ScheduledExecutorTask scheduledExecutorTask(Runnable runnable) {
		var scheduledExecutorTask = new ScheduledExecutorTask();
		scheduledExecutorTask.setPeriod(50);
		scheduledExecutorTask.setRunnable(runnable);
		return scheduledExecutorTask;
	}

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		var taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(50);
		taskExecutor.setMaxPoolSize(100);
		taskExecutor.setAllowCoreThreadTimeOut(true);
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		return taskExecutor;
	}

	@Bean
	public ConcurrentTaskExecutor virtualThreadsTaskExecutor() {
		var virtualThreadsExecutor = Executors.newVirtualThreadPerTaskExecutor();
		return new ConcurrentTaskExecutor(virtualThreadsExecutor);
	}
}
