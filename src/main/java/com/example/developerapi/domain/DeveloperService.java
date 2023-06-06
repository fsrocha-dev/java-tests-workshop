package com.example.developerapi.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {
	private DeveloperRepository developerRepository;

	public DeveloperService(DeveloperRepository developerRepository) {
		this.developerRepository = developerRepository;
	}

	public Developer create(Developer developer) {
		return developerRepository.save(developer);
	}

	public Optional<Developer> get(Long id) {
		return developerRepository.findById(id);
	}

	public Optional<Developer> getByName(String name) {
		return developerRepository.findByName(name);
	}

	public List<Developer> list(String terrain, String climate) {
		Example<Developer> query = QueryBuilder.makeQuery(new Developer(climate, terrain));
		return developerRepository.findAll(query);
	}

	public void remove(Long id) {
		developerRepository.deleteById(id);
	}
}
