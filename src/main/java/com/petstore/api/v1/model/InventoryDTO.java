package com.petstore.api.v1.model;

import com.petstore.reference.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryDTO {

	private OrderStatus orderStatus;

	private Long countOfOrders;

}
