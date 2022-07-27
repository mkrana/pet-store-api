package com.petstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petstore.api.v1.model.CategoryDTO;
import com.petstore.domain.Category;
import com.petstore.mapper.CategoryMapper;
import com.petstore.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

	@Mock
	CategoryRepository categoryRepository;

	CategoryServiceImpl categoryService;

	@BeforeEach
	void setUp() {
		categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
	}

	@Test
	void testFindItemsByCategory() {
		Category category = new Category();
		category.setName("Dogs");
		CategoryDTO categoryDto = new CategoryDTO();
		categoryDto.setName("Dogs");
		when(categoryRepository.findByName(anyString())).thenReturn(Arrays.asList(category));
		CategoryDTO entityReturned = categoryService.findItemsByCategory("Dogs");
		assertEquals("Dogs", entityReturned.getName());
		verify(categoryRepository).findByName(anyString());

	}

	@Test
	void testFindAllCategories() {

		when(categoryRepository.findAll()).thenReturn(Arrays.asList(new Category(), new Category(), new Category()));
		List<CategoryDTO> categories = categoryService.findAllCategories();
		assertEquals(3, categories.size());
		verify(categoryRepository, times(1)).findAll();
	}

}
