package com.example.developerapi.domain;

import javax.validation.constraints.NotNull;

public class DeveloperDTO {

	@NotNull
	private String name;

	@NotNull
	private String skill;

	@NotNull
	private String level;

	public DeveloperDTO(Developer developer) {
		this.name = developer.getName();
		this.skill = developer.getSkill();
		this.level = developer.getLevel();
	}

	public DeveloperDTO(String name, String skill, String level) {
		this.name = name;
		this.skill = skill;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
