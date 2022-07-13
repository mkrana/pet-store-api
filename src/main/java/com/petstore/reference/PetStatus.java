package com.petstore.reference;

import java.util.stream.Stream;

public enum PetStatus {

	AVAILABLE("available"), PENDING("pending"), SOLD("sold");

	private String status;

	private PetStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	// Pet Stream - Purely to avoid loops :)
	public static Stream<PetStatus> stream() {
		return Stream.of(PetStatus.values());
	}

}
