package com.apress.spring6recipes.bookshop;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BookShopCashier implements Cashier {

	private final BookShop bookShop;

	public BookShopCashier(BookShop bookShop) {
		this.bookShop = bookShop;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void checkout(List<String> isbns, String username) {
		isbns.forEach(isbn -> bookShop.purchase(isbn, username));
	}

}
