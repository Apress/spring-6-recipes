package com.apress.spring6recipes.bookshop;

public interface BookShop {

	void purchase(String isbn, String username);
	void increaseStock(String isbn, int stock);
	int checkStock(String isbn);
}
