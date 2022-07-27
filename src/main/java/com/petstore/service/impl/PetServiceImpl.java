package com.petstore.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.petstore.api.v1.model.PetDTO;
import com.petstore.domain.Pet;
import com.petstore.mapper.PetMapper;
import com.petstore.reference.PetStatus;
import com.petstore.repository.PetRepository;
import com.petstore.service.PetService;

@Service
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

	@Override
	public List<PetDTO> getPetsByStatus(String petStatus) {
		Optional<PetStatus> match = PetStatus.stream().filter(status -> petStatus.equalsIgnoreCase(status.toString()))
				.findFirst();
		if (match.isPresent()) {
			return petRepository.findByPetStatus(match.get()).stream().map(petMapper::toPetDTO)
					.collect(Collectors.toList());
		} else {
			// TODO: Exception handling needed
			return Arrays.asList();
		}

	}

	@Override
	public PetDTO savePet(final PetDTO petDTO) {
		// Verify if the pet status is valid

		boolean isValidPetStatus = PetStatus.stream()
				.anyMatch(petStatus -> petDTO.getPetStatus().equalsIgnoreCase(petStatus.getStatus()));
		if (isValidPetStatus) {
			Pet savedPet = petRepository.save(petMapper.toPet(petDTO));
			return petMapper.toPetDTO(savedPet);
		} else {
			// TODO - Exception handling needed
			throw new RuntimeException("No Such PetStatus Exists");
		}
	}

}
