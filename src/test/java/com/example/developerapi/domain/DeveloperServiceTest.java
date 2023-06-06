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

	@Test
	public void listDevelopers_ReturnsAllDevelopers() {
		List<Developer> developers = new ArrayList<>() {
			{
				add(DEVELOPER);
			}
		};
		Example<Developer> query = QueryBuilder.makeQuery(new Developer(DEVELOPER.getSkill(), DEVELOPER.getLevel()));
		when(developerRepository.findAll(query)).thenReturn(developers);

		List<Developer> sut = developerService.list(DEVELOPER.getSkill(), DEVELOPER.getLevel());

		assertThat(sut).isNotEmpty();
		assertThat(sut).hasSize(1);
		assertThat(sut.get(0)).isEqualTo(DEVELOPER);
	}

	@Test
  public void listDevelopers_ReturnsNoDeveloper() {
		// esse any() o argumento não interessa para o teste/nao afeta a logica
		// para não precisar de construir uma query que não vai ser utilizada.
    when(developerRepository.findAll(any())).thenReturn(Collections.emptyList());

    List<Developer> sut = developerService.list(DEVELOPER.getSkill(), DEVELOPER.getLevel());

    assertThat(sut).isEmpty();
  }

	// Como o ato de deletar não retorna nada, aqui podemos testar que pelo menos
	// não acontece alguma exception
	@Test
	public void removeDeveloper_WithExistingId_doesNotThrowAnyException() {
		// Esse assertThatCode() nos ajuda a verificar o retorno de uma exceção
		// No caso queremos saber se o método não lança uma exceção.
		assertThatCode(() -> developerService.remove(1L)).doesNotThrowAnyException();
	}

	@Test
	public void removeDeveloper_WithUnexistingId_ThrowsException() {
		// when não pode ser void pq ele recebe um método callable que deve retornar
		// algo
		// usamos o doThrow() para informar a exceção a ser lançada e depois a condição
		doThrow(new RuntimeException()).when(developerRepository).deleteById(99L);

		assertThatThrownBy(() -> developerService.remove(99L)).isInstanceOf(RuntimeException.class);
	}
}
