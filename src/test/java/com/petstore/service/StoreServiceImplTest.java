package com.petstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petstore.api.v1.model.OrderDTO;
import com.petstore.domain.Order;
import com.petstore.mapper.OrderMapper;
import com.petstore.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

	@Mock
	OrderRepository orderRepository;

	@Mock
	OrderMapper orderMapper;

	@InjectMocks
	StoreServiceImpl storeService;

	private static final Long ID = 2L;

	@Test
	void testGetOrderById() {
		Order order = new Order();
		order.setId(ID);
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(ID);
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
		when(orderMapper.toOrderDTO(any())).thenReturn(orderDTO);
		OrderDTO dto = storeService.getOrderById(ID);
		assertNotNull(dto);
		assertEquals(dto, orderDTO);
	}

	// @Test
	void testGetInventory() {
		fail("Not yet implemented"); // TODO
	}

}
