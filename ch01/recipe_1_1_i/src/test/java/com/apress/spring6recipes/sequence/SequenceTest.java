package com.apress.spring6recipes.sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceTest {

	@Test
	public void generatedSequence() {
		Sequence seq = new Sequence();
		seq.setPrefix("TE");
		seq.setSuffix("ST");

		assertEquals("TE0ST", seq.nextValue());
		assertEquals("TE1ST", seq.nextValue());
	}

}