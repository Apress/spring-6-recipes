package com.apress.spring6recipes.sequence;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

public class Sequence {

	private final AtomicInteger counter = new AtomicInteger();

	private final String suffix;

	private final int initial;

	@Autowired
	private PrefixGenerator prefixGenerator;

	public Sequence(String suffix, int initial) {
		this.prefixGenerator = prefixGenerator;
		this.suffix = suffix;
		this.initial = initial;
	}

	public Sequence(PrefixGenerator prefixGenerator, String suffix, int initial) {
		this.prefixGenerator = prefixGenerator;
		this.suffix = suffix;
		this.initial = initial;
	}

	public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}

	public String nextValue() {
		return prefixGenerator.getPrefix() + (initial + counter.getAndIncrement()) + suffix;
	}

}
