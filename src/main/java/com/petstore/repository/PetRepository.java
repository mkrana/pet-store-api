package com.petstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petstore.domain.Pet;
import com.petstore.reference.PetStatus;

public interface PetRepository extends JpaRepository<Pet, Long> {

	List<Pet> findByName(String name);

	Optional<Pet> findById(Long id);

	List<Pet> findByPetStatus(PetStatus petStatus);

}
