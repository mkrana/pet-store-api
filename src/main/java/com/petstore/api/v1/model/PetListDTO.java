package com.petstore.api.v1.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetListDTO {

	List<PetDTO> pets;

}
