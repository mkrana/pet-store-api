package com.petstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petstore.api.v1.model.UserDTO;
import com.petstore.domain.User;
import com.petstore.mapper.UserMapper;
import com.petstore.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	private static final String PWD = "ABC@LANME@Gamils";

	private static final long USER_ID = 3L;

	private static final String PHONE_NMB = "31324-233-23";

	private static final String EMAIL = "FNameLName@email.com";

	private static final String LNAME = "Lname";

	private static final String FNAME = "FName";

	@Mock
	UserRepository userRepository;

	UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	UserServiceImpl userService;

	UserDTO userDTO;

	@BeforeEach
	void setUp() throws Exception {
		userDTO = new UserDTO();
		userDTO.setUsername(FNAME + LNAME);
		userDTO.setFirstName(FNAME);
		userDTO.setLastName(LNAME);
		userDTO.setEmail(EMAIL);
		userDTO.setPhone(PHONE_NMB);
		userDTO.setUserId(USER_ID);
		userDTO.setPassword(PWD);

		userService = new UserServiceImpl(userRepository, userMapper);
	}

	@Test
	void testCreateUser() {
		// Perform Stubbing for persistence layer
		when(userRepository.save(any(User.class))).thenReturn(userMapper.toUser(userDTO));

		// Invoke the method execution
		UserDTO user = userService.createUser(userDTO);

		// Assertions
		assertNotNull(user);
		assertEquals(user.getFirstName(), userDTO.getFirstName());
		assertEquals(user, userDTO);

	}

	@Test
	void testFindUserByUserName() {
		when(userRepository.findByUsername(FNAME + LNAME)).thenReturn(Optional.of(userMapper.toUser(userDTO)));
		UserDTO persistedEntity = userService.findUserByUserName(FNAME + LNAME);
		assertNotNull(persistedEntity);
		assertEquals(userDTO, persistedEntity);
	}

	@Test
	void updateUser() {
		final String updatedFName = "Royal";
		final String updatedLName = "Jack";
		// Updated User DTO Values
		when(userRepository.findByUsername(FNAME + LNAME)).thenReturn(Optional.of(userMapper.toUser(userDTO)));

		// Update the userDTO to indicate an update in value
		userDTO.setFirstName(updatedFName);
		userDTO.setLastName(updatedLName);
		when(userRepository.save(any(User.class))).thenReturn(userMapper.toUser(userDTO));

		UserDTO savedEntity = userService.updateUser(FNAME + LNAME, userDTO);
		assertNotNull(savedEntity);
		assertEquals(savedEntity, userDTO);
		assertEquals(updatedFName, savedEntity.getFirstName());
		assertEquals(updatedLName, savedEntity.getLastName());

	}
}
