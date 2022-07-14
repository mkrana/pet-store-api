package com.petstore.service;

import java.util.Map;

import com.petstore.api.v1.model.OrderDTO;
import com.petstore.reference.OrderStatus;

public interface StoreService {

	public OrderDTO getOrderById(Long id);

	public Map<OrderStatus, Long> getInventory();

}
