package com.apress.spring6recipes.nosql;

import jakarta.annotation.PreDestroy;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
class Neo4jStarwarsRepository implements StarwarsRepository {

	private final Neo4jTemplate neo4j;

	Neo4jStarwarsRepository(Neo4jTemplate neo4j) {
		this.neo4j = neo4j;
	}

	@Override
	public Planet save(Planet planet) {
		return neo4j.save(planet);
	}

	@Override
	public Character save(Character character) {
		return neo4j.save(character);
	}

	@Override
	public Iterable<Character> findAllCharacters() {
		return neo4j.find(Character.class).all();
	}

	@Override
	public Iterable<Planet> findAllPlanets() {
		return neo4j.find(Planet.class).all();
	}

	@PreDestroy
	public void cleanUp() {
		// Clean up when shutdown
		neo4j.deleteAll(Character.class);
		neo4j.deleteAll(Planet.class);
	}
}
