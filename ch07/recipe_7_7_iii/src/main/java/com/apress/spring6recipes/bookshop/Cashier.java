package com.apress.spring6recipes.bookshop;

import java.util.List;

public interface Cashier {

	public void checkout(List<String> isbns, String username);

}
