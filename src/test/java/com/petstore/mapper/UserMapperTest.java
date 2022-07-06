package com.petstore.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.petstore.api.v1.model.UserDTO;
import com.petstore.domain.User;

class UserMapperTest {

	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void toUserDtoTest() {
		User user = new User();
		user.setEmail("FNameLName@gmail.com");
		user.setFirstName("FName");
		user.setLastName("LName");
		user.setId(1L);
		user.setPhone("123456678");
		user.setUserStatus(1L);
		user.setUsername("FName");
		user.setPassword("X123");
		UserDTO dto = userMapper.toUserDTO(user);
		assertEquals(user.getId(), dto.getUserId());
		assertEquals(user.getEmail(), dto.getEmail());
		assertEquals(user.getFirstName(), dto.getFirstName());
		assertEquals(user.getLastName(), dto.getLastName());
		assertEquals(user.getPassword(), dto.getPassword());
		assertEquals(user.getPhone(), dto.getPhone());
		assertEquals(user.getUserStatus(), dto.getUserStatus());
	}

	@Test
	void toUserTest() {
		UserDTO userDto = new UserDTO();
		userDto.setEmail("FNameLName@gmail.com");
		userDto.setFirstName("FName");
		userDto.setLastName("LName");
		userDto.setUserId(1L);
		userDto.setPhone("123456678");
		userDto.setUserStatus(1L);
		userDto.setUsername("FName");
		userDto.setPassword("X123");
		User user = userMapper.toUser(userDto);
		assertEquals(userDto.getUserId(), user.getId());
		assertEquals(userDto.getEmail(), user.getEmail());
		assertEquals(userDto.getFirstName(), user.getFirstName());
		assertEquals(userDto.getLastName(), user.getLastName());
		assertEquals(userDto.getPassword(), user.getPassword());
		assertEquals(userDto.getPhone(), user.getPhone());
		assertEquals(userDto.getUserStatus(), user.getUserStatus());

	}

}
