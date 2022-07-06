package com.petstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petstore.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
