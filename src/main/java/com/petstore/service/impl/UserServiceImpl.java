package com.petstore.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petstore.api.v1.model.UserDTO;
import com.petstore.domain.User;
import com.petstore.mapper.UserMapper;
import com.petstore.repository.UserRepository;
import com.petstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User userEntity = userRepository.save(userMapper.toUser(userDTO));
		return userMapper.toUserDTO(userEntity);
	}

	@Override
	public UserDTO findUserByUserName(String userName) {
		Optional<User> userOptional = userRepository.findByUsername(userName);
		if (userOptional.isPresent()) {
			return userMapper.toUserDTO(userOptional.get());
		} else {
			// TODO: Construct better Response
			return new UserDTO();
		}
	}

	public boolean deleteUser(String username) {
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			userRepository.deleteById(userOptional.get().getId());
			return true;
		} else {
			// TODO: Construct better Response
			return false;
		}
	}

	@Override
	public UserDTO updateUser(String username, UserDTO userDTO) {
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			User savedEntity = userOptional.get();
			userDTO.setUserId(savedEntity.getId());
			UserDTO savedDTO = createUser(userDTO);
			return savedDTO;
		} else {
			// TODO: Construct better Response
			return new UserDTO();
		}
	}

}
