package com.example.developerapi.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.example.developerapi.constants.DeveloperConstants.DEVELOPER;
import static com.example.developerapi.constants.DeveloperConstants.INVALID_DEVELOPER;

@DataJpaTest
public class DeveloperRepositoryTest {

	@Autowired
	private DeveloperRepository developerRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void createDeveloper_WithValidData_ReturnDeveloper() {
		Developer developer = developerRepository.save(DEVELOPER);

		Developer sut = entityManager.find(Developer.class, developer.getId());

		assertThat(sut).isNotNull();
		assertThat(sut.getName()).isEqualTo(DEVELOPER.getName());
		assertThat(sut.getLevel()).isEqualTo(DEVELOPER.getLevel());
		assertThat(sut.getSkill()).isEqualTo(DEVELOPER.getSkill());
	}

	@Test
	public void createDeveloper_WithInvalidData_ReturnThrowsException() {
		Developer emptyDeveloper = new Developer();
		Developer invalidDeveloper = new Developer("", "", "");

		assertThatThrownBy(() -> developerRepository.save(emptyDeveloper)).isInstanceOf(RuntimeException.class);
		assertThatThrownBy(() -> developerRepository.save(invalidDeveloper)).isInstanceOf(RuntimeException.class);
	}

	@Test
	public void getDeveloper_ReturnDeveloper() {
		Developer developer = developerRepository.save(DEVELOPER);

		Developer sut = developerRepository.findById(developer.getId()).get();
		Developer devData = entityManager.find(Developer.class, developer.getId());

		assertThat(sut).isNotNull();
		assertThat(sut.getName()).isEqualTo(devData.getName());
		assertThat(sut.getLevel()).isEqualTo(devData.getLevel());
		assertThat(sut.getSkill()).isEqualTo(devData.getSkill());
	}

}
