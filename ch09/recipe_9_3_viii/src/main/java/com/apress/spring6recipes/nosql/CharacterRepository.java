package com.apress.spring6recipes.nosql;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CharacterRepository extends ReactiveCrudRepository<Character, String> { }
