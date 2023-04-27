package com.apress.spring6recipes.nosql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Character {

	private final String name;
	private final List<Character> friends = new ArrayList<>();

	private String id;
	private Planet location;
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
}
