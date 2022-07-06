package com.petstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.petstore.api.v1.model.CategoryDTO;
import com.petstore.domain.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	@Mapping(source = "id", target = "categoryId")
	public CategoryDTO toCategoryDTO(Category category);

	@Mapping(source = "categoryId", target = "id")
	public Category toCategory(CategoryDTO categoryDTO);

}
