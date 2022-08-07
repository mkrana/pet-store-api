package com.petstore.service;

import com.petstore.api.v1.model.UserDTO;

public interface UserService {

	public UserDTO createUser(UserDTO userDTO);

	public UserDTO findUserByUserName(String userName);

	public boolean deleteUser(String username);

	public UserDTO updateUser(String username, UserDTO userDTO);
	
	public UserDTO patchUser(String username, UserDTO userDTO);

}
