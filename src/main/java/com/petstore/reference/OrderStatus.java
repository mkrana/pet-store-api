package com.petstore.reference;

public enum OrderStatus {

	PLACED("placed"), APPROVED("approved"), DELIVERED("delivered");

	String status;

	private OrderStatus(String status) {
		this.status = status;
	}

}
