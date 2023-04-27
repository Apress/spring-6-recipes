package com.apress.spring6recipes.executors;

import com.apress.spring6recipes.utils.Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DemonstrationRunnable implements Runnable {

	public void run() {
		Utils.sleep(50);
		System.out.printf("%s : Hello at %s%n", Thread.currentThread().getName(), LocalDateTime.now());
	}

}