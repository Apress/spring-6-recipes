package com.apress.spring6recipes.sequence;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component("sequenceDao")
class SimpleSequenceDao implements SequenceDao {

	private final Map<String, Sequence> sequences = new ConcurrentHashMap<>();
	private final Map<String, AtomicInteger> values = new ConcurrentHashMap<>();

	SimpleSequenceDao() {
		sequences.put("IT", new Sequence("IT", "30", "A"));
		values.put("IT", new AtomicInteger(10000));
	}

	public Sequence getSequence(String sequenceId) {
		return sequences.get(sequenceId);
	}

	public int getNextValue(String sequenceId) {
		var value = values.get(sequenceId);
		return value.getAndIncrement();
	}

}
