package com.petstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petstore.api.v1.model.InventoryDTO;
import com.petstore.api.v1.model.OrderDTO;
import com.petstore.domain.Order;
import com.petstore.mapper.OrderMapper;
import com.petstore.reference.OrderStatus;
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

	@Test
	void testGetInventory() {
		InventoryDTO inventoryDTO = new InventoryDTO(OrderStatus.APPROVED, 2L);
		InventoryDTO inventoryDTO2 = new InventoryDTO(OrderStatus.PLACED, 3L);
		when(orderRepository.findByStatusAndCountGroupByStatus()).thenReturn(List.of(inventoryDTO, inventoryDTO2));
		Map<OrderStatus, Long> inventoryMap = storeService.getInventory();
		assertEquals(2, inventoryMap.size());
		assertNotNull(inventoryMap.get(OrderStatus.APPROVED));
		assertNull(inventoryMap.get(OrderStatus.DELIVERED));
		assertEquals(3, inventoryMap.get(OrderStatus.PLACED));
	}

}
