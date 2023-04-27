package com.apress.spring6recipes.bookshop.reactive;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

public class TransactionalR2dbcBookShop implements BookShop {

	private final ReactiveTransactionManager txManager;
	private final DatabaseClient client;

	public TransactionalR2dbcBookShop(ReactiveTransactionManager txManager,
																		ConnectionFactory cf) {
		this.txManager = txManager;
		this.client = DatabaseClient.create(cf);
	}

	public Mono<Void> purchase(String isbn, String username) {
		var def = new DefaultTransactionDefinition();
		var tx = txManager.getReactiveTransaction(def);

		var BOOK_SQL = "SELECT PRICE FROM BOOK WHERE ISBN = $1";
		var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = $1";
		var BALANCE_SQL = "UPDATE ACCOUNT SET BALANCE = BALANCE - $1 WHERE USERNAME = $2";
		return tx.flatMap((status) -> {
			var price = client.sql(BOOK_SQL).bind("$1", isbn)
							.map((row, meta) -> row.get("PRICE", Integer.class))
							.one();
			var stock = price.doOnNext((p) -> client.sql(STOCK_SQL)
							.bind("$1", price).fetch());
			var balance = stock.doOnNext((p) -> client.sql(BALANCE_SQL)
							.bind("$1", price)
							.bind("$2", username).fetch());
			return balance.then(txManager.commit(status))
					      .onErrorResume((ex) -> txManager.rollback(status).then(Mono.error(ex)));
		});
	}
}
