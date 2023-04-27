package com.apress.spring6recipes.bookshop;

public class Book {

	private String isbn;

	private String name;

	private int price;


	public Book() {
	}

	public Book(String isbn, String name, int price) {
		this.isbn = isbn;
		this.name = name;
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
