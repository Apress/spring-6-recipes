package com.apress.spring6recipes.sequence;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {

	private final AtomicInteger counter = new AtomicInteger();
	private final String suffix;
	private PrefixGenerator prefixGenerator = () -> "";

	public Sequence(String suffix, int initial) {
		this.suffix = suffix;
		this.counter.set(initial);
	}

	public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}

	public String getSequence() {
		return prefixGenerator.getPrefix() + counter.getAndIncrement() + suffix;
	}
}
