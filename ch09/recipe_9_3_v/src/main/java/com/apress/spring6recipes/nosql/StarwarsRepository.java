package com.apress.spring6recipes.nosql;

public interface StarwarsRepository {

	Planet save(Planet planet);
	Character save(Character charachter);

	Iterable<Character> findAllCharacters();
	Iterable<Planet> findAllPlanets();

	}
