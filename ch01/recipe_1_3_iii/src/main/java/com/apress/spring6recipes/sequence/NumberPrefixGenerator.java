package com.apress.spring6recipes.sequence;

import java.util.concurrent.ThreadLocalRandom;

public class NumberPrefixGenerator implements PrefixGenerator {

	public String getPrefix() {
		var randomInt = ThreadLocalRandom.current().nextInt(100);
		return String.format("%03d", randomInt);
	}

}
