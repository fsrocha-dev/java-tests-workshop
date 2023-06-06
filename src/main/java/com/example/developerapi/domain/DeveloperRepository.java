package com.example.developerapi.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface DeveloperRepository extends CrudRepository<Developer, Long>, QueryByExampleExecutor<Developer> {
	Optional<Developer> findByName(String name);

	@Override
	<S extends Developer> List<S> findAll(Example<S> example);
}
