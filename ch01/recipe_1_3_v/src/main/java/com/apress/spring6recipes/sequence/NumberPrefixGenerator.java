package com.apress.spring6recipes.sequence;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class NumberPrefixGenerator implements PrefixGenerator {

	public String getPrefix() {
		var randomGenerator = ThreadLocalRandom.current();
		int randomInt = randomGenerator.nextInt(100);
		return String.format("%03d", randomInt);
	}

}
