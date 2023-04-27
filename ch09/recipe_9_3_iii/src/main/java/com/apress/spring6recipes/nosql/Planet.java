package com.apress.spring6recipes.nosql;

public class Planet {

	private final String name;
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

}
