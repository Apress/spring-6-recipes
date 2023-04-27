package com.apress.spring6recipes.nosql;

import jakarta.annotation.PreDestroy;
import org.neo4j.driver.Driver;

import java.util.Map;

class Neo4jStarwarsRepository implements StarwarsRepository {

	private static final String CREATE_PLANET_QUERY = "CREATE (a:Planet {id: randomUUID(), name: $name}) RETURN a.id";
	private static final String CREATE_CHARACTER_QUERY = "CREATE (a:Character {id: randomUUID(), name: $name}) RETURN a.id";

	private static final String CREATE_PLANET_REL_QUERY = "MATCH (a:Character), (b:Planet) WHERE a.id=$aid AND b.id=$bid CREATE (a)-[r:LOCATION]->(b)";
	private static final String CREATE_FRIENDS_REL_QUERY = "MATCH (a:Character), (b:Character) WHERE a.id=$aid AND b.id=$bid CREATE (a)-[r:FRIENDS_WITH]->(b)";
	private static final String CREATE_MASTER_REL_QUERY = "MATCH (a:Character), (b:Character) WHERE a.id=$aid AND b.id=$bid CREATE (a)-[r:MASTER_OF]->(b)";
	private static final String DELETE_ALL_QUERY = "MATCH (n) DETACH DELETE n";

	private final Driver db;

	Neo4jStarwarsRepository(Driver db) {
		this.db = db;
	}

	@Override
	public Planet save(Planet planet) {
		if (planet.getId() != null) {
			return planet;
		}

		try (var session = db.session()) {
			var res = session.run(CREATE_PLANET_QUERY, Map.of("name", planet.getName()));
			planet.setId(res.single().get(0).asString());
			return planet;
		}
	}

	@Override
	public Character save(Character character) {
		if (character.getId() != null) {
			return character;
		}

		try (var session = db.session()) {
			session.writeTransaction(tx -> {
				var res = tx.run(CREATE_CHARACTER_QUERY, Map.of( "name", character.getName()));
				character.setId(res.single().get(0).asString());
				if (character.getLocation() != null) {
					Planet location = save(character.getLocation());
					tx.run(CREATE_PLANET_REL_QUERY, Map.of("aid", character.getId(), "bid", location.getId()));
				}
				for (var friend : character.getFriends()) {
					friend = save(friend);
					tx.run(CREATE_FRIENDS_REL_QUERY, Map.of("aid", character.getId(), "bid", friend.getId()));
				}

				if (character.getApprentice() != null) {
					var apprentice = save(character.getApprentice());
					tx.run(CREATE_MASTER_REL_QUERY, Map.of("aid", character.getId(), "bid", apprentice.getId()));
				}
				return null;
			});
		}
		return character;
	}

	@PreDestroy
	public void cleanUp() {
		// Clean up when shutdown
		try (var session = db.session()) {
			session.run("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r");
		}
	}

}
