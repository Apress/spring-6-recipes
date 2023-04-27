package com.apress.spring6recipes.reactive.court.web;

import com.apress.spring6recipes.reactive.court.domain.SportType;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@Repository
public class SportTypeRepository {

	private final List<SportType> sportTypes = List.of(
					new SportType(1, "Tennis"),
					new SportType(2, "Soccer"),
					new SportType(3, "Swimming"));

	public Flux<SportType> findAll() {
		return Flux.fromIterable(this.sportTypes);
	}

	public Optional<SportType> findById(int id) {
		return sportTypes.stream().filter( (type) -> type.id() == id).findFirst();
	}
}
