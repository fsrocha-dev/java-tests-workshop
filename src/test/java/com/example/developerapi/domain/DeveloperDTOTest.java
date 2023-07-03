package com.example.developerapi.domain;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeveloperDTOTest {
	private Validator validator;

	public DeveloperDTOTest() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	@Test
	public void testeDeveloperDTOValidation() {
		DeveloperDTO developer = new DeveloperDTO(null, null, null);

		var violations = validator.validate(developer);

		Assertions.assertFalse(violations.isEmpty());
		Assertions.assertEquals(3, violations.size());
	}
}
