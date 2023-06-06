package com.example.developerapi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import static com.example.developerapi.constants.DeveloperConstants.DEVELOPER;
import static com.example.developerapi.constants.DeveloperConstants.INVALID_DEVELOPER;;

@ExtendWith(MockitoExtension.class)
public class DeveloperServiceTest {

	@InjectMocks
	private DeveloperService developerService;

	@Mock
	private DeveloperRepository developerRepository;

	@Test
	public void createDeveloper_WithValidData_ReturnDeveloper() {
		when(developerRepository.save(DEVELOPER)).thenReturn(DEVELOPER);

		Developer sut = developerService.create(DEVELOPER);

		assertThat(sut).isEqualTo(DEVELOPER);
	}

	@Test
	public void createDeveloper_WithInvalidData_ThrowsExecption() {
		when(developerRepository.save(INVALID_DEVELOPER)).thenThrow(RuntimeException.class);

		assertThatThrownBy(() -> developerService.create(INVALID_DEVELOPER)).isInstanceOf(RuntimeException.class);
	}

	@Test
  public void getDeveloper_ByExistingId_ReturnsDeveloper() {
		// Esse teste cobre 2 cenários: 404 ou 200
    when(developerRepository.findById(1L)).thenReturn(Optional.of(DEVELOPER));

    Optional<Developer> sut = developerService.get(1L);

    assertThat(sut).isNotEmpty();
    assertThat(sut.get()).isEqualTo(DEVELOPER);
  }
	@Test
  public void getDeveloper_ByUnexistingId_ReturnsEmpty() {
    when(developerRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<Developer> sut = developerService.get(1L);

    assertThat(sut).isEmpty();
  }

	@Test
  public void getDeveloper_ByExistingName_ReturnsDeveloper() {
    when(developerRepository.findByName(DEVELOPER.getName())).thenReturn(Optional.of(DEVELOPER));

    Optional<Developer> sut = developerService.getByName(DEVELOPER.getName());

    assertThat(sut).isNotEmpty();
    assertThat(sut.get()).isEqualTo(DEVELOPER);
  }

	@Test
	public void getDeveloper_ByUnexistingName_ReturnsEmpty() {
		final String name = "Unexisting name";
		when(developerRepository.findByName(name)).thenReturn(Optional.empty());

		Optional<Developer> sut = developerService.getByName(name);

		assertThat(sut).isEmpty();
	}
}
