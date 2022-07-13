package com.petstore.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.petstore.api.v1.model.CategoryDTO;
import com.petstore.api.v1.model.PetDTO;
import com.petstore.domain.Category;
import com.petstore.domain.Pet;
import com.petstore.reference.PetStatus;

class PetMapperTest {

	PetMapper petMapper = Mappers.getMapper(PetMapper.class);

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testToPetDTO() {
		Category category = new Category();
		category.setId(1L);
		category.setName("Dog");
		Pet pet = new Pet();
		pet.setId(1L);
		pet.setName("Pluto");
		pet.setCategory(category);
		pet.setPetStatus(PetStatus.AVAILABLE);

		PetDTO petDto = petMapper.toPetDTO(pet);
		assertEquals(pet.getId(), petDto.getPetId());
		assertEquals(pet.getName(), petDto.getName());
		assertEquals(pet.getPetStatus().toString(), petDto.getPetStatus());
		assertNotNull(petDto.getCategory());
		assertEquals(pet.getCategory().getId(), petDto.getCategory().getCategoryId());
		assertEquals(pet.getCategory().getName(), petDto.getCategory().getName());

	}

	@Test
	void testToPet() {
		CategoryDTO category = new CategoryDTO();
		category.setCategoryId(1L);
		category.setName("Dog");
		PetDTO pet = new PetDTO();
		pet.setPetId(1L);
		pet.setName("Pluto");
		pet.setCategory(category);
		pet.setPetStatus(PetStatus.AVAILABLE.toString());

		Pet mappedPet = petMapper.toPet(pet);
		assertEquals(pet.getPetId(), mappedPet.getId());
		assertEquals(pet.getName(), mappedPet.getName());
		assertEquals(pet.getPetStatus(), mappedPet.getPetStatus().toString());
		assertNotNull(mappedPet.getCategory());
		assertEquals(pet.getCategory().getCategoryId(), mappedPet.getCategory().getId());
		assertEquals(pet.getCategory().getName(), mappedPet.getCategory().getName());

	}

}
