package com.apress.spring6recipes.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceService {

	@Autowired
	private SequenceDao sequenceDao;

	public String generate(String sequenceId) {
		var sequence = sequenceDao.getSequence(sequenceId);
		var value = sequenceDao.getNextValue(sequenceId);
		return sequence.prefix() + value + sequence.suffix();
	}
}
