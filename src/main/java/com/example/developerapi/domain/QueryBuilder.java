package com.example.developerapi.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class QueryBuilder {
	public static Example<Developer> makeQuery(Developer developer) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
		return Example.of(developer, exampleMatcher);
	}
}
