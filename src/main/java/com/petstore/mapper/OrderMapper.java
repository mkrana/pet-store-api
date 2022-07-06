package com.petstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.petstore.api.v1.model.OrderDTO;
import com.petstore.domain.Order;

@Mapper
public interface OrderMapper {

	@Mapping(source = "orderId", target = "id")
	public Order toOrder(OrderDTO orderDTO);

	@Mapping(source = "id", target = "orderId")
	public OrderDTO toOrderDTO(Order order);

}
