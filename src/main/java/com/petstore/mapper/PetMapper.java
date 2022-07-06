package com.petstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.petstore.api.v1.model.PetDTO;
import com.petstore.domain.Pet;

@Mapper
public interface PetMapper {

	@Mapping(source = "id", target = "petId")
	@Mapping(source = "category.id", target = "category.categoryId")
	public PetDTO toPetDTO(Pet pet);

	@Mapping(source = "category.categoryId", target = "category.id")
	@Mapping(source = "petId", target = "id")
	public Pet toPet(PetDTO petDTO);

}
