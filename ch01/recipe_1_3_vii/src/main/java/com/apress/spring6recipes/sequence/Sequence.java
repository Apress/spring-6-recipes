package com.apress.spring6recipes.sequence;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {

	private final AtomicInteger counter = new AtomicInteger();

	private final PrefixGenerator prefixGenerator;

	private final String suffix;

	private final int initial;

	public Sequence(PrefixGenerator prefixGenerator, String suffix, int initial) {
		this.prefixGenerator = prefixGenerator;
		this.suffix = suffix;
		this.initial = initial;
	}

	public String nextValue() {
		return prefixGenerator.getPrefix() + (initial + counter.getAndIncrement()) + suffix;
	}

}
