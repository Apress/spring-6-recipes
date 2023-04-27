package com.apress.spring6recipes.executors;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {

	public static void main(String[] args) throws Throwable {
		Runnable task = new DemonstrationRunnable();

		// will create a pool of threads and attempt to
		// reuse previously created ones if possible
		var cachedThreadPoolExecutorService = Executors.newCachedThreadPool();
		if (cachedThreadPoolExecutorService.submit(task).get() == null)
			System.out.printf("The cachedThreadPoolExecutorService has succeeded at %s%n", LocalDateTime.now());

		// limits how many new threads are created, queueing the rest
		var fixedThreadPool = Executors.newFixedThreadPool(100);
		if (fixedThreadPool.submit(task).get() == null)
			System.out.printf("The fixedThreadPool has succeeded at %s%n", LocalDateTime.now());

		// doesn't use more than one thread at a time
		var singleThreadExecutorService = Executors.newSingleThreadExecutor();
		if (singleThreadExecutorService.submit(task).get() == null)
			System.out.printf("The singleThreadExecutorService has succeeded at %s%n", LocalDateTime.now());

		// support sending a job with a known result
		var es = Executors.newCachedThreadPool();
		if (es.submit(task, Boolean.TRUE).get().equals(Boolean.TRUE))
			System.out.println("Job has finished!");

		// mimic TimerTask
		var scheduledThreadExecutorService = Executors.newScheduledThreadPool(10);
		if (scheduledThreadExecutorService.schedule(task, 30, TimeUnit.SECONDS).get() == null)
			System.out.printf("The scheduledThreadExecutorService has succeeded at %s%n", LocalDateTime.now());

		// this doesn't stop until it encounters
		// an exception or its cancel()ed
		scheduledThreadExecutorService.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);

	}

}