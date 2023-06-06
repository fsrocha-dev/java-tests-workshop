package com.example.developerapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "developers")
public class Developer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String skill;
	private String level;

	public Developer() {
	}

	public Developer(String skill, String level) {
		this.skill = skill;
		this.level = level;
	}

	public Developer(String name, String skill, String level) {
		this.name = name;
		this.skill = skill;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getskill() {
		return skill;
	}

	public void setskill(String skill) {
		this.skill = skill;
	}

	public String getlevel() {
		return level;
	}

	public void setlevel(String level) {
		this.level = level;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(obj, this);
	}
}
