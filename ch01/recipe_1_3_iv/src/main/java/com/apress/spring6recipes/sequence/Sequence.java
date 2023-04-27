package com.apress.spring6recipes.sequence;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {

	private final AtomicInteger counter = new AtomicInteger();
	private final String suffix;
	private final int initial;
	private PrefixGenerator prefixGenerator;

	public Sequence(PrefixGenerator prefixGenerator, String suffix, int initial) {
		this.prefixGenerator = prefixGenerator;
		this.suffix = suffix;
		this.initial = initial;
	}

	// @Autowired
	public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}

	@Autowired
	public void myOwnCustomInjectionName(PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}

	public String nextValue() {
		return prefixGenerator.getPrefix() + (initial + counter.getAndIncrement()) + suffix;
	}

}
