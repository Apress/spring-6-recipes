package com.apress.spring6recipes.bookshop.reactive;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.annotation.Transactional;

import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

public class TransactionalR2dbcBookShop implements BookShop {

	private final DatabaseClient client;

	public TransactionalR2dbcBookShop(ConnectionFactory cf) {
		this.client = DatabaseClient.create(cf);
	}

	@Transactional
	public Mono<Void> purchase(String isbn, String username) {
		var BOOK_SQL = "SELECT PRICE FROM BOOK WHERE ISBN = $1";
		var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = $1";
		var BALANCE_SQL = "UPDATE ACCOUNT SET BALANCE = BALANCE - $1 WHERE USERNAME = $2";

		var price = client.sql(BOOK_SQL).bind("$1", isbn)
											.map((row, meta) -> row.get("PRICE", Integer.class))
											.one();
		var stock = price.doOnNext((p) -> client.sql(STOCK_SQL).bind("$1", price).fetch());
		var balance = stock.doOnNext((p) -> client.sql(BALANCE_SQL)
																							.bind("$1", price)
																							.bind("$2", username).fetch());
		return balance.then();
	}
}
