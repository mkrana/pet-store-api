package com.petstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petstore.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	public List<Category> findByName(String name);

}
