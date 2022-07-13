package com.petstore.controllers.v1;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petstore.api.v1.model.PetDTO;
import com.petstore.api.v1.model.PetListDTO;
import com.petstore.service.PetService;

@Controller
@RequestMapping("/api/v1/pets")
public class PetController {

	private final PetService petService;

	public PetController(PetService petService) {
		this.petService = petService;
	}

	// Three APIs to be hosted
	// 1. Returns pet based on ID
	// 2. Returns pet based on Availability Status
	// 3. Returns all the pets currently present

	@GetMapping("/list")
	public ResponseEntity<PetListDTO> getAllPets() {
		return new ResponseEntity<>(new PetListDTO(petService.getAllPets()), HttpStatus.OK);
	}

	@GetMapping("/id/{petId}")
	public ResponseEntity<PetListDTO> getPetById(@PathVariable String petId) {
		PetListDTO petListDTO = new PetListDTO(Arrays.asList(petService.getPetById(Long.valueOf(petId))));
		return new ResponseEntity<>(petListDTO, HttpStatus.OK);
	}

	@GetMapping("/status/{petStatus}")
	public ResponseEntity<PetListDTO> getPetByStatus(@PathVariable String petStatus) {
		return new ResponseEntity<>(new PetListDTO(petService.getPetsByStatus(petStatus)), HttpStatus.OK);
	}

}
