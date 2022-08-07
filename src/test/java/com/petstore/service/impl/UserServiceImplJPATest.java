package com.petstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.petstore.api.v1.model.UserDTO;
import com.petstore.mapper.UserMapper;
import com.petstore.repository.UserRepository;
import com.petstore.service.UserService;

@DataJpaTest
class UserServiceImplJPATest {

	@Autowired
	UserRepository userRepository;

	UserServiceImpl userService;
	
	UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	@BeforeEach
	void setUp() {
		userService = new UserServiceImpl(userRepository, userMapper);

		// Setting up the record which has to be updated in the test
		UserDTO user = new UserDTO();
		user.setEmail("user@email.com");
		user.setFirstName("userFName");
		user.setLastName("userLName");
		user.setPassword("ABC123XZY");
		user.setUserStatus(0L);
		user.setPhone("323-2323-4344");
		user.setUsername("FNameLName");
		userService.createUser(user);
	}

	@Test
	void testPatchUser() {

		String userNameToBeUpdated = "FNameLName";
		String updatedFirstName = "UpdatedFName";
		String updatedLastName = "UpdatedLastName";

		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(updatedFirstName);
		userDTO.setLastName(updatedLastName);
		UserDTO savedEntity = userService.patchUser(userNameToBeUpdated, userDTO);
		assertNotNull(savedEntity);
		assertEquals(savedEntity.getFirstName(), updatedFirstName);

	}

}
