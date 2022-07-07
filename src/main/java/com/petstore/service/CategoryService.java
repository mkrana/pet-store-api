package com.petstore.service;

import java.util.List;

import com.petstore.api.v1.model.CategoryDTO;

public interface CategoryService {
	
	public CategoryDTO findItemsByCategory(String categoryName);
	
	public List<CategoryDTO> findAllCategories();
	
}
