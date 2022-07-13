package com.petstore.api.v1.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderDTO {

	private Long orderId;
	private LocalDate shipDate;
	private Integer quantity;
	private String status;
	private PetDTO petId;
	private Boolean isComplete;

}
