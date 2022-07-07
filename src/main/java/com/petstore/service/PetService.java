package com.petstore.service;

import java.util.List;

import com.petstore.api.v1.model.PetDTO;

public interface PetService {

	List<PetDTO> getPetByName(String name);

	PetDTO getPetById(Long id);

	List<PetDTO> getAllPets();
}
