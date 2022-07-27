package com.petstore.controllers.v1;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.api.v1.model.CategoryDTO;
import com.petstore.api.v1.model.PetDTO;
import com.petstore.reference.PetStatus;
import com.petstore.service.PetService;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

	@Mock
	PetService petService;

	@InjectMocks
	PetController petController;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

	}

	@Test
	void testGetPetById() throws Exception {
		PetDTO petDTO = new PetDTO();
		petDTO.setPetId(123L);
		when(petService.getPetById(Long.valueOf("123"))).thenReturn(petDTO);
		mockMvc.perform(get("/api/v1/pets/id/123").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.pets", hasSize(1)));

	}

	@Test
	void testGetAllPets() throws Exception {
		when(petService.getAllPets()).thenReturn(Arrays.asList(new PetDTO(), new PetDTO(), new PetDTO()));
		mockMvc.perform(get("/api/v1/pets/list").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.pets", hasSize(3)));
	}

	@Test
	void testGetPetByStatus() throws Exception {
		PetDTO petDTO = new PetDTO();
		petDTO.setPetStatus(PetStatus.PENDING.toString());
		PetDTO petDTO2 = new PetDTO();
		petDTO2.setPetStatus(PetStatus.PENDING.toString());
		when(petService.getPetsByStatus(PetStatus.PENDING.toString())).thenReturn(Arrays.asList(petDTO, petDTO2));
		mockMvc.perform(get("/api/v1/pets/status/PENDING").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.pets", hasSize(2)));

	}

	@Test
	void testSavePet() throws Exception {
		PetDTO petDTO = new PetDTO();
		petDTO.setName("Roxane");
		petDTO.setPetStatus("AVAILABLE");
		CategoryDTO category = new CategoryDTO();
		category.setCategoryId(1L);
		petDTO.setCategory(category);
		ObjectMapper objectMapper = new ObjectMapper();
		String petString = objectMapper.writeValueAsString(petDTO);
		// petService Stubbing
		mockMvc.perform(post("/api/v1/pets").contentType(MediaType.APPLICATION_JSON).content(petString))
				.andExpect(status().isCreated());

	}

}
