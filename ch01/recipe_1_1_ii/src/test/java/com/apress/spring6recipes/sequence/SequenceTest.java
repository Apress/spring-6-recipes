package com.apress.spring6recipes.sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceTest {

	@Test
	public void generatedSequence() {
		Sequence seq = new Sequence("TEST-SEQ", "TE", "ST");
		assertEquals("TEST-SEQ", seq.getId());
		assertEquals("TE", seq.getPrefix());
		assertEquals("ST", seq.getSuffix());
	}

}