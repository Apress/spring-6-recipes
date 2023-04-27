package com.apress.spring6recipes.sequence;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {

	private final AtomicInteger counter = new AtomicInteger();
	private final String prefix;
	private final String suffix;

	public Sequence(String prefix, String suffix, int initial) {
		this.prefix = prefix;
		this.suffix = suffix;
		this.counter.set(initial);
	}

	public String nextValue() {
		return prefix + counter.getAndIncrement() + suffix;
	}
}