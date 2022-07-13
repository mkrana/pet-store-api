package com.petstore.api.v1.model;

import lombok.Data;

@Data
public class PetDTO {

	private Long petId;
	private CategoryDTO category;
	private String name;
	private String petStatus;

} 
