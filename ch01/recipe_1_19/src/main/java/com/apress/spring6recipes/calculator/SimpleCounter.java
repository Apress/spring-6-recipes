package com.apress.spring6recipes.calculator;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCounter implements Counter {

	private final AtomicInteger counter = new AtomicInteger();

	@Override
	public void increase() {
		counter.incrementAndGet();
	}

	@Override
	public int getCount() {
		return counter.get();
	}
}
