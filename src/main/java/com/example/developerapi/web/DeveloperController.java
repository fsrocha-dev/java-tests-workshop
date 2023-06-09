package com.example.developerapi.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.developerapi.domain.Developer;
import com.example.developerapi.domain.DeveloperDTO;
import com.example.developerapi.domain.DeveloperService;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
	@Autowired
	private DeveloperService developerService;

	@PostMapping
	public ResponseEntity<Developer> create(@RequestBody @Valid DeveloperDTO developer) {
		Developer developerCreated = developerService
				.create(new Developer(developer.getName(), developer.getSkill(), developer.getLevel()));
		return ResponseEntity.status(HttpStatus.CREATED).body(developerCreated);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Developer> get(@PathVariable("id") Long id) {
		return developerService.get(id).map(developer -> ResponseEntity.ok(developer))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Developer> getByName(@PathVariable("name") String name) {
		return developerService.getByName(name).map(developer -> ResponseEntity.ok(developer))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<Developer>> list(@RequestParam(required = false) String skill,
			@RequestParam(required = false) String level) {
		List<Developer> developers = developerService.list(skill, level);
		return ResponseEntity.ok(developers);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
		developerService.remove(id);
		return ResponseEntity.noContent().build();
	}
}
