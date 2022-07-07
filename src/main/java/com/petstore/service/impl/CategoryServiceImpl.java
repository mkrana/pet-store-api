package com.petstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.petstore.api.v1.model.CategoryDTO;
import com.petstore.mapper.CategoryMapper;
import com.petstore.repository.CategoryRepository;
import com.petstore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	private final CategoryMapper categoryMapper;

	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public CategoryDTO findItemsByCategory(String categoryName) {
		return categoryRepository.findByName(categoryName).stream().map(categoryMapper::toCategoryDTO).findFirst()
				.get();
	}

	@Override
	public List<CategoryDTO> findAllCategories() {
		return categoryRepository.findAll().stream().map(categoryMapper::toCategoryDTO).collect(Collectors.toList());
	}

}
