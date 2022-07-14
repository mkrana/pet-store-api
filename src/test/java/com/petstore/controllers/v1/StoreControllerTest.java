package com.petstore.controllers.v1;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.petstore.api.v1.model.OrderDTO;
import com.petstore.reference.OrderStatus;
import com.petstore.service.StoreServiceImpl;

@ExtendWith(MockitoExtension.class)
class StoreControllerTest {

	@Mock
	StoreServiceImpl storeService;

	@InjectMocks
	StoreController storeController;

	private static final Long ID = 1L;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();
	}

	@Test
	void testGetOrderById() throws Exception {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(ID);
		orderDTO.setQuantity(2);
		when(storeService.getOrderById(anyLong())).thenReturn(orderDTO);
		mockMvc.perform(get("/api/v1/store/order/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.orderId", is(1))).andExpect(jsonPath("$.quantity", is(2)));

	}

	@Test
	void inventoryTest() throws Exception {
		Map<OrderStatus, Long> inventoryMap = Map.of(OrderStatus.APPROVED, 3L, OrderStatus.DELIVERED, 4L);
		when(storeService.getInventory()).thenReturn(inventoryMap);
		mockMvc.perform(get("/api/v1/store/inventory").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.APPROVED", is(3)))
				.andExpect(jsonPath("$.DELIVERED", is(4)));
	}

}
