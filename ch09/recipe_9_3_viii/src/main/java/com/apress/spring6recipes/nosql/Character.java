package com.apress.spring6recipes.nosql;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Node
public class Character {

	private final String name;
	@Relationship(type="FRIENDS_WITH")
	private @AccessType(AccessType.Type.FIELD) List<Character> friends = new ArrayList<>();

	@Id
	@GeneratedValue(UUIDStringGenerator.class)
	private String id;
	@Relationship(type = "LOCATION")
	private Planet location;
	@Relationship(type = "MASTER_OF")
	private Character apprentice;

	public Character(String name) {
		this.name=name;
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

	public Planet getLocation() {
		return location;
	}

	public void setLocation(Planet location) {
		this.location = location;
	}

	public Character getApprentice() {
		return apprentice;
	}

	public void setApprentice(Character apprentice) {
		this.apprentice = apprentice;
	}

	public List<Character> getFriends() {
		return Collections.unmodifiableList(friends);
	}

	public void addFriend(Character friend) {
		friends.add(friend);
	}

	@Override
	public String toString() {
		return String.format("Character[name=%s, planet=%s]", this.name, this.location != null ? this.location.getName() : "");
	}
}
