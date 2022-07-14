package com.petstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petstore.api.v1.model.InventoryDTO;
import com.petstore.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("select new com.petstore.api.v1.model.InventoryDTO(status, count(*)) from orders group by status")
	public List<InventoryDTO> findByStatusAndCountGroupByStatus();

}
