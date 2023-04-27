package com.apress.spring6recipes.bookshop.reactive;

import reactor.core.publisher.Mono;

public interface BookShop {

	Mono<Void> purchase(String isbn, String username);

}
