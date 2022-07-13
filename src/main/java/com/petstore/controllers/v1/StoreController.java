package com.petstore.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petstore.api.v1.model.OrderDTO;
import com.petstore.service.StoreService;

@Controller
@RequestMapping("/api/v1/store")
public class StoreController {

	private final StoreService storeService;

	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderDTO> orderById(@PathVariable String orderId) {
		return new ResponseEntity<>(storeService.getOrderById(Long.valueOf(orderId)), HttpStatus.OK);
	}

}
