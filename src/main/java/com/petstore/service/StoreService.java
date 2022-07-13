package com.petstore.service;

import com.petstore.api.v1.model.InventoryDTO;
import com.petstore.api.v1.model.OrderDTO;

public interface StoreService {

	public OrderDTO getOrderById(Long id);

	public InventoryDTO getInventory();

}
