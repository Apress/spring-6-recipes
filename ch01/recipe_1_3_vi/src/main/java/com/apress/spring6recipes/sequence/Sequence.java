package com.apress.spring6recipes.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {

	private final AtomicInteger counter = new AtomicInteger();

	@Autowired
	@Qualifier("datePrefixGenerator")
	private PrefixGenerator prefixGenerator;
	private final String suffix;

	public Sequence(PrefixGenerator prefixGenerator, String suffix, int initial) {
		this.prefixGenerator = prefixGenerator;
		this.suffix = suffix;
		this.counter.set(initial);
	}

	@Autowired
	public void setPrefixGenerator(
					@Qualifier("datePrefixGenerator") PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}

	public void setInitial(int initial) {
		this.counter.set(initial);
	}

	public synchronized String getSequence() {
		return prefixGenerator.getPrefix() + counter.getAndIncrement() + suffix;
	}
}
