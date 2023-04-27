package com.apress.spring6recipes.sequence;

import org.springframework.beans.factory.annotation.Autowired;

public class Sequence {

	@Autowired
	private PrefixGenerator prefixGenerator;
	private String suffix;
	private int initial;
	private int counter;

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

	public synchronized String getSequence() {
		var builder = new StringBuilder();
		builder.append(prefixGenerator.getPrefix());
		builder.append(initial + counter++);
		builder.append(suffix);
		return builder.toString();
	}

}
