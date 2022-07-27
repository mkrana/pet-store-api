package com.petstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petstore.api.v1.model.CategoryDTO;
import com.petstore.api.v1.model.PetDTO;
import com.petstore.domain.Category;
import com.petstore.domain.Pet;
import com.petstore.mapper.PetMapper;
import com.petstore.reference.PetStatus;
import com.petstore.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {

	PetMapper petMapper = Mappers.getMapper(PetMapper.class);

	@Mock
	PetRepository petRepository;

	PetServiceImpl petService;

	@BeforeEach
	void setUp() {
		petService = new PetServiceImpl(petMapper, petRepository);
	}

	@Test
	void testGetPetByName() {
		Pet roscoe = new Pet();
		roscoe.setName("Roscoe");
		Pet roscoe2 = new Pet();
		roscoe2.setName("Roscoe");
		PetDTO petDto = new PetDTO();
		petDto.setName("Roscoe");
		List<Pet> petList = new ArrayList<Pet>();
		petList.add(roscoe2);
		petList.add(roscoe);

		when(petRepository.findByName("Roscoe")).thenReturn(petList);
		List<PetDTO> listOfPets = petService.getPetByName("Roscoe");
		assertNotNull(listOfPets);
		assertEquals(2, listOfPets.size());
		boolean result = listOfPets.stream().allMatch(element -> element.getName().equalsIgnoreCase("Roscoe"));
		assertTrue(result);
	}

	@Test
	void testGetPetById() {
		Pet pet = new Pet();
		pet.setId(1L);
		PetDTO petDto = new PetDTO();
		petDto.setPetId(1L);
		when(petRepository.findById(anyLong())).thenReturn(Optional.of(pet));
		PetDTO petObject = petService.getPetById(1L);
		assertNotNull(petObject);
		assertEquals(1L, petObject.getPetId());
		verify(petRepository).findById(anyLong());
	}

	@Test
	void testGetAllPets() {
		List<Pet> pets = Arrays.asList(new Pet(), new Pet(), new Pet());

		when(petRepository.findAll()).thenReturn(pets);
		List<PetDTO> petDTOs = petService.getAllPets();
		assertEquals(pets.size(), petDTOs.size());
		assertNotNull(petDTOs);
		verify(petRepository, times(1)).findAll();

	}

	@Test
	void testGetPetByPetStatus() {
		Pet petOne = new Pet();
		petOne.setPetStatus(PetStatus.PENDING);
		Pet petTwo = new Pet();
		petTwo.setPetStatus(PetStatus.PENDING);
		List<Pet> pets = Arrays.asList(petOne, petTwo);
		when(petRepository.findByPetStatus(any())).thenReturn(pets);
		PetDTO petDTOOne = new PetDTO();
		petDTOOne.setPetStatus(PetStatus.PENDING.toString());
		List<PetDTO> petDtos = petService.getPetsByStatus(PetStatus.AVAILABLE.toString());
		assertNotNull(petDtos);
		assertEquals(pets.size(), petDtos.size());

	}

	@Test
	void testSavePetCorrectPetStatus() {

		PetDTO petDTO = new PetDTO();
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setName("Dog");
		categoryDTO.setCategoryId(2L);
		petDTO.setName("Roxane");
		petDTO.setPetStatus("AVAILABLE");
		petDTO.setPetId(1L);
		petDTO.setCategory(categoryDTO);

		Pet pet = new Pet();
		pet.setName("Roxane");
		pet.setPetStatus(PetStatus.AVAILABLE);
		Category category = new Category();
		category.setName("Dog");
		category.setId(2L);
		pet.setId(1L);
		pet.setCategory(category);

		when(petRepository.save(any(Pet.class))).thenReturn(pet);
		PetDTO savedPetDTO = petService.savePet(petDTO);

		assertNotNull(savedPetDTO);
		assertEquals(petDTO.getName(), savedPetDTO.getName());
		assertEquals(petDTO, savedPetDTO);
		assertEquals(petDTO.getCategory(), savedPetDTO.getCategory());
	}

}
