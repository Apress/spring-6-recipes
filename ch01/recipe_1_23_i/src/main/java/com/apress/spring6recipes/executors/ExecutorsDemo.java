package com.apress.spring6recipes.executors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {

	public static void main(String[] args) throws Throwable {
		var task = new DemonstrationRunnable();
		// will create a pool of threads and attempt to
		// reuse previously created ones if possible
		try (var cachedThreadPoolExecutorService = Executors.newCachedThreadPool()) {
			if (cachedThreadPoolExecutorService.submit(task).get() == null)
				printStatus(cachedThreadPoolExecutorService);
		}

		// limits how many new threads are created, queueing the rest
		try (var fixedThreadPool = Executors.newFixedThreadPool(100)) {
			if (fixedThreadPool.submit(task).get() == null)
				printStatus(fixedThreadPool);
		}

		// doesn't use more than one thread at a time
		try (var singleThreadExecutorService = Executors.newSingleThreadExecutor()) {
			if (singleThreadExecutorService.submit(task).get() == null)
				printStatus(singleThreadExecutorService);
		}

		// support sending a job with a known result
		try (var es = Executors.newCachedThreadPool()) {
			if (es.submit(task, Boolean.TRUE).get().equals(Boolean.TRUE))
				System.out.println("Job has finished!");
		}

		// Create a Virtual Thread per Launched task
		try (var vt = Executors.newVirtualThreadPerTaskExecutor()) {
			if (vt.submit(task).get() == null) {
				printStatus(vt);
			}
		}

		// mimic TimerTask
		try (var scheduledThreadExecutorService = Executors.newScheduledThreadPool(10)) {
			if (scheduledThreadExecutorService.schedule(task, 30, TimeUnit.SECONDS).get() == null)
				printStatus(scheduledThreadExecutorService);

			// this doesn't stop until it encounters
			// an exception or its cancel()ed
			scheduledThreadExecutorService.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
		}

	}

	static void printStatus(Object executor) {
		var type = executor.getClass().getSimpleName();
		var datetime = LocalDateTime.now();
		System.out.printf("The %s has succeeded at %s%n", type, datetime.format(DateTimeFormatter.ISO_DATE_TIME));
	}

}