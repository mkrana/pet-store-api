package com.petstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petstore.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
