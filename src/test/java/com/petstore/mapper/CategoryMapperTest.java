package com.petstore.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.petstore.api.v1.model.CategoryDTO;
import com.petstore.domain.Category;

class CategoryMapperTest {

	CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
	
	@Test
	void testToCategory() {
		CategoryDTO dto = new CategoryDTO();
		dto.setCategoryId(1L);
		dto.setName("Dogs");
		Category category = categoryMapper.toCategory(dto);
		assertEquals(dto.getCategoryId(), category.getId());
		assertEquals(dto.getName(), category.getName());

	}

	@Test
	void testToCategoryDTO() {
		Category source = new Category();
		source.setId(1L);
		source.setName("Dogs");
		CategoryDTO target = categoryMapper.toCategoryDTO(source);
		assertEquals(source.getId(), target.getCategoryId());
		assertEquals(source.getName(), target.getName());
	}

}
