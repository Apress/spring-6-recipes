package com.apress.spring6recipes.todo;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class Todo {

	private Long id;
	private String owner;
	private boolean completed = false;

	@NotEmpty
	private String description;

	public Todo() {}

	public Todo(String owner, String description) {
		this.owner = owner;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {

		return String.format("Todo [id=%d, description=%s, owner=%s, completed=%b]%n", this.id, this.description,
				this.owner, this.completed);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Todo todo = (Todo) o;
		return this.id != null && Objects.equals(this.id, todo.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
