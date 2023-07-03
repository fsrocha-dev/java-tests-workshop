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
		// Developer data = new Developer(developer.getName(), developer.getSkill(),
		// developer.getLevel());
		return developerRepository.save(developer);
	}

	public Optional<Developer> get(Long id) {
		return developerRepository.findById(id);
	}

	public Optional<Developer> getByName(String name) {
		return developerRepository.findByName(name);
	}

	public List<Developer> list(String skill, String level) {
		Example<Developer> query = QueryBuilder.makeQuery(new Developer(skill, level));
		return developerRepository.findAll(query);
	}

	public void remove(Long id) {
		developerRepository.deleteById(id);
	}
}
