package com.apress.spring6recipes.nosql;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
class Neo4jStarwarsService implements StarwarsService {

	private final PlanetRepository planetRepository;
	private final CharacterRepository characterRepository;

	Neo4jStarwarsService(PlanetRepository planetRepository,
											 CharacterRepository characterRepository) {
		this.planetRepository = planetRepository;
		this.characterRepository = characterRepository;
	}

	@Override
	public Mono<Planet> save(Planet planet) {
		return planetRepository.save(planet);
	}

	@Override
	public Mono<Character> save(Character character) {
		return characterRepository.save(character);
	}

	@Override
	public Mono<Void> printAll() {
		return planetRepository.findAll().doOnNext(System.out::println)
						.thenMany(characterRepository.findAll().doOnNext(System.out::println)).then();
	}

	@PreDestroy
	public Mono<Void> deleteAll() {
		return characterRepository.deleteAll()
						.then(planetRepository.deleteAll());
	}
}
