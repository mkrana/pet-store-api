package com.petstore.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.petstore.reference.PetStatus;

import lombok.Data;

@Entity(name = "pets")
@Data
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private PetStatus petStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id", name = "CATEGORY_ID")
	private Category category;

}
