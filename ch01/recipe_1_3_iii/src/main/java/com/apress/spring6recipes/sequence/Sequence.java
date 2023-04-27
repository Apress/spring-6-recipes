package com.apress.spring6recipes.sequence;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

public class Sequence {

	private final AtomicInteger counter = new AtomicInteger();

	private final String suffix;

	private final int initial;

	@Autowired
	private PrefixGenerator[] prefixGenerators;

	public Sequence(String suffix, int initial) {
		this.suffix = suffix;
		this.initial = initial;
	}

	public String getSequence() {
		var builder = new StringBuilder();
		for (var prefix : prefixGenerators) {
			builder.append(prefix.getPrefix());
			builder.append('-');
		}
		builder.append(initial + counter.getAndIncrement()).append(suffix);
		return builder.toString();
	}

}
