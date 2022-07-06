package com.petstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.petstore.api.v1.model.UserDTO;
import com.petstore.domain.User;

@Mapper
public interface UserMapper {

	@Mapping(source = "id", target = "userId")
	public UserDTO toUserDTO(User user);

	@Mapping(source = "userId", target = "id")
	public User toUser(UserDTO userDto);

} 