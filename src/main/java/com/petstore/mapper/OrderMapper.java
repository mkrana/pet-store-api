package com.petstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.petstore.api.v1.model.OrderDTO;
import com.petstore.domain.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	@Mapping(source = "orderId", target = "id")
	@Mapping(source = "petId.petId", target = "petId.id")
	@Mapping(source = "petId.category.categoryId", target = "petId.category.id")
	public Order toOrder(OrderDTO orderDTO);

	@Mapping(source = "id", target = "orderId")
	@Mapping(source = "petId.id", target = "petId.petId")
	@Mapping(source = "petId.category.id", target = "petId.category.categoryId")
	public OrderDTO toOrderDTO(Order order);

}
