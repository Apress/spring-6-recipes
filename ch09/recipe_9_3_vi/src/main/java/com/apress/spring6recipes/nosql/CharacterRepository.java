package com.apress.spring6recipes.nosql;

import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character, String> { }
