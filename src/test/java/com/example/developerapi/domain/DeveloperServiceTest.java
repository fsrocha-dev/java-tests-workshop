package com.example.developerapi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
