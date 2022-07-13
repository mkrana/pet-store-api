package com.petstore.api.v1.model;

import java.util.List;

import com.petstore.reference.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryDTO {

	List<OrderStatus> orderStatus;

}
