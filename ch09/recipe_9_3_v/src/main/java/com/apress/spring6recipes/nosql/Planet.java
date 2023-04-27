package com.apress.spring6recipes.nosql;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node
public class Planet {

	private final String name;

	@Id
	@GeneratedValue(UUIDStringGenerator.class)
	private String id;

	public Planet(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	void setId(String id) {
		this.id=id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("Planet[name=%s]", this.name);
	}
}
