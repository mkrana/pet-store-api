package com.petstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petstore.api.v1.model.CategoryDTO;
import com.petstore.domain.Category;
import com.petstore.mapper.CategoryMapper;
import com.petstore.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
	CategoryMapper categoryMapper;

	@Mock
	CategoryRepository categoryRepository;

	@InjectMocks
	CategoryServiceImpl categoryService;

	@Test
	void testFindItemsByCategory() {
		Category category = new Category();
		category.setName("Dogs");
		CategoryDTO categoryDto = new CategoryDTO();
		categoryDto.setName("Dogs");
		when(categoryRepository.findByName(anyString())).thenReturn(Arrays.asList(category));
		when(categoryMapper.toCategoryDTO(any())).thenReturn(categoryDto);
		CategoryDTO entityReturned = categoryService.findItemsByCategory("Dogs");
		assertEquals("Dogs", entityReturned.getName());
		verify(categoryRepository).findByName(anyString());

	}

	@Test
	void testFindAllCategories() {

		when(categoryRepository.findAll()).thenReturn(Arrays.asList(new Category(), new Category(), new Category()));
		when(categoryMapper.toCategoryDTO(any())).thenReturn(new CategoryDTO());
		List<CategoryDTO> categories = categoryService.findAllCategories();
		assertEquals(3, categories.size());
		verify(categoryMapper, times(categories.size())).toCategoryDTO(any());
		verify(categoryRepository, times(1)).findAll();
	}

}
