package com.petstore.service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.petstore.api.v1.model.InventoryDTO;
import com.petstore.api.v1.model.OrderDTO;
import com.petstore.domain.Order;
import com.petstore.mapper.OrderMapper;
import com.petstore.reference.OrderStatus;
import com.petstore.repository.OrderRepository;

@Service
public class StoreServiceImpl implements StoreService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;

	public StoreServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
		super();
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
	}

	@Override
	public OrderDTO getOrderById(Long id) {
		Optional<Order> orderOptional = orderRepository.findById(id);
		if (orderOptional.isPresent()) {
			return orderMapper.toOrderDTO(orderOptional.get());
		} else {
			// TODO: Return Object Not found instead of incorrect object.
			return new OrderDTO();
		}
	}

	@Override
	public Map<OrderStatus, Long> getInventory() {
		return orderRepository.findByStatusAndCountGroupByStatus().stream()
				.collect(Collectors.toMap(InventoryDTO::getOrderStatus, InventoryDTO::getCountOfOrders));
	}

}
