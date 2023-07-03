package com.example.developerapi.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

import com.example.developerapi.domain.DeveloperService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.developerapi.constants.DeveloperConstants.DEVELOPER;

@WebMvcTest(DeveloperController.class)
// @AutoConfigureMockMvc
public class DeveloperControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DeveloperService developerService;

	@Test
	public void createDeveloper_WithValidDataDTO_ReturnCreated() throws Exception {
		when(developerService.create(DEVELOPER)).thenReturn(DEVELOPER);

		mockMvc
				.perform(
						post("/developers").content(objectMapper.writeValueAsString(DEVELOPER))
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value(DEVELOPER.getName()));
	}

}
