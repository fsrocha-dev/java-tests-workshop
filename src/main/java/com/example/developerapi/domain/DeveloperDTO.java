package com.example.developerapi.domain;

public class DeveloperDTO {
	private String name;
	private String skill;
	private String level;

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
