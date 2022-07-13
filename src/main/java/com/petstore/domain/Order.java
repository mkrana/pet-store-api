package com.petstore.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.petstore.reference.OrderStatus;

import lombok.Data;

@Entity(name = "orders")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(referencedColumnName = "id", name = "PET_ID")
	private Pet petId;
	private Integer quantity;
	private LocalDate shipDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	private Boolean isComplete;

}
