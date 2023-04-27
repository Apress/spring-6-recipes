package com.apress.spring6recipes.caching;


import java.io.Serializable;

public record Customer(long id, String name) implements Serializable { }
