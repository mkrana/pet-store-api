package com.petstore.api.v1.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderDTO {

	private Long orderId;
	private Long petId;
	private LocalDate shipDate;
	private String status;

}
