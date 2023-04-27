package com.apress.spring6recipes.nosql;

import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends CrudRepository<Planet, String> { }
