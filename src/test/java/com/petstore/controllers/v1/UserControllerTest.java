package com.petstore.controllers.v1;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.api.v1.model.UserDTO;
import com.petstore.exception.UserNotFoundException;
import com.petstore.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	UserServiceImpl userService;

	@InjectMocks
	UserController userController;

	MockMvc mockMvc;

	UserDTO userDTO;

	private static final String PWD = "ABC@LANME@Gamils";

	private static final long USER_ID = 3L;

	private static final String PHONE_NMB = "31324-233-23";

	private static final String EMAIL = "FNameLName@email.com";

	private static final String LNAME = "Lname";

	private static final String FNAME = "FName";

	private static final String USERNAME = "FNameLName";

	@BeforeEach
	void setUp() throws Exception {
		userDTO = new UserDTO();
		userDTO.setFirstName(FNAME);
		userDTO.setLastName(LNAME);
		userDTO.setEmail(EMAIL);
		userDTO.setPhone(PHONE_NMB);
		userDTO.setUserId(USER_ID);
		userDTO.setPassword(PWD);
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.setControllerAdvice(RestResponseEntityExceptionHandler.class).build();

	}

	@Test
	void testCreateUser() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String userStringObject = objectMapper.writeValueAsString(userDTO);
		// Persisted entity must have a user Id. This would get overwritten during setUp
		// invocation
		userDTO.setUserId(2L);
		when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

		mockMvc.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON).content(userStringObject))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.firstName", is(FNAME)))
				.andExpect(jsonPath("$.lastName", is(LNAME))).andExpect(jsonPath("$.email", is(EMAIL)));
	}

	// Method to test if exception is thrown
	@Test
	void testGetUserByUserName() throws Exception {
		when(userService.findUserByUserName(anyString())).thenThrow(UserNotFoundException.class);
		mockMvc.perform(get("/api/v1/user/" + USERNAME)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.type", is("RESOURCE_NOT_PRESENT")))
				.andExpect(jsonPath("$.code", is(101)));
		verify(userService, times(1)).findUserByUserName(anyString());
	}

}
