package com.petstore.api.v1.model;

import lombok.Data;

@Data
public class UserDTO {

	private Long userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private long userStatus;

}
