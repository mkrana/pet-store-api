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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petstore.api.v1.model.PetDTO;
import com.petstore.domain.Pet;
import com.petstore.mapper.PetMapper;
import com.petstore.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {

	@Mock
	PetMapper petMapper;

	@Mock
	PetRepository petRepository;

	@InjectMocks
	PetServiceImpl petService;

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
		when(petMapper.toPetDTO(any())).thenReturn(petDto);
		// Invoke getPetByName
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
		when(petMapper.toPetDTO(any())).thenReturn(petDto);
		// Invoke the method
		PetDTO petObject = petService.getPetById(1L);
		assertNotNull(petObject);
		assertEquals(1L, petObject.getPetId());
		verify(petRepository).findById(anyLong());
	}

	@Test
	void testGetAllPets() {
		List<Pet> pets = Arrays.asList(new Pet(), new Pet(), new Pet());

		when(petRepository.findAll()).thenReturn(pets);
		when(petMapper.toPetDTO(any())).thenReturn(new PetDTO());
		List<PetDTO> petDTOs = petService.getAllPets();
		assertEquals(pets.size(), petDTOs.size());
		assertNotNull(petDTOs);
		verify(petRepository, times(1)).findAll();
		verify(petMapper, times(petDTOs.size())).toPetDTO(any());

	}

}
