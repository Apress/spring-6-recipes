package com.apress.spring6recipes.sequence;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.annotation.Resource;

public class Sequence {

	private final AtomicInteger counter = new AtomicInteger();

	@Resource(name = "datePrefixGenerator")
	private PrefixGenerator prefixGenerator;

	private String suffix;

	private int initial;

	public Sequence() {
	}

	public Sequence(PrefixGenerator prefixGenerator, String suffix, int initial) {
		this.prefixGenerator = prefixGenerator;
		this.suffix = suffix;
		this.initial = initial;
	}

	public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setInitial(int initial) {
		this.initial = initial;
	}

	public String nextValue() {
		return prefixGenerator.getPrefix() + (initial + counter.getAndIncrement()) + suffix;
	}

}
