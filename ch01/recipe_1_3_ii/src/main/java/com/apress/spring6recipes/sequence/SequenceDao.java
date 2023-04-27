package com.apress.spring6recipes.sequence;

public interface SequenceDao {

	Sequence getSequence(String sequenceId);

	int getNextValue(String sequenceId);

}
