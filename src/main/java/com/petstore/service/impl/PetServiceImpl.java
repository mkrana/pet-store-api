package com.petstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.petstore.api.v1.model.PetDTO;
import com.petstore.mapper.PetMapper;
import com.petstore.repository.PetRepository;
import com.petstore.service.PetService;

public class PetServiceImpl implements PetService {

	private final PetMapper petMapper;

	private final PetRepository petRepository;

	public PetServiceImpl(PetMapper petMapper, PetRepository petRepository) {
		super();
		this.petMapper = petMapper;
		this.petRepository = petRepository;
	}

	@Override
	public List<PetDTO> getPetByName(String name) {
		return petRepository.findByName(name).stream().map(petMapper::toPetDTO)
				.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public PetDTO getPetById(Long id) {
		return petRepository.findById(id).stream().map(petMapper::toPetDTO).findFirst().get();
	}

	@Override
	public List<PetDTO> getAllPets() {
		return petRepository.findAll().stream().map(petMapper::toPetDTO).collect(Collectors.toList());
	}

}
