package com.apress.spring6recipes.executors;

import com.apress.spring6recipes.utils.Utils;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class DemonstrationRunnable implements Runnable {

	@Override
	public void run() {
		Utils.sleep(1, TimeUnit.SECONDS);

		System.out.printf("Hello at %s from %s%n",
						LocalDate.now(), Thread.currentThread().getName());
	}
}