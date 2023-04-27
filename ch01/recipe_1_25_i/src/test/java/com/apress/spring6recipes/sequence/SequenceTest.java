package com.apress.spring6recipes.sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceTest {

	@Test
	public void generatedSequence() {
		var seq = new Sequence("TE", "ST", 0);
		assertEquals("TE0ST", seq.nextValue());
		assertEquals("TE1ST", seq.nextValue());
	}
}