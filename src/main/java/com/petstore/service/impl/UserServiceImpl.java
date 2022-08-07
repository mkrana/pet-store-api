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
			throw new RuntimeException("Entity Not Found");
		}
	}

	public boolean deleteUser(String username) {
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			userRepository.deleteById(userOptional.get().getId());
			return true;
		} else {
			// TODO: Construct better Response
			throw new RuntimeException("Entity Not Found");
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
			throw new RuntimeException("Entity Not Found");
		}
	}

	@Override
	public UserDTO patchUser(String username, UserDTO userDTO) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		// Compare if the value has changed using if else conditions
		if (optionalUser.isPresent()) {
			User entity = optionalUser.get();
			// TODO - Find a better pay to patch values from DTO objects.
			if (userDTO.getEmail() != null) {
				entity.setEmail(userDTO.getEmail());
			}
			if (userDTO.getFirstName() != null) {
				entity.setFirstName(userDTO.getFirstName());
			}
			if (userDTO.getLastName() != null) {
				entity.setLastName(userDTO.getLastName());
			}
			if (userDTO.getPassword() != null) {
				entity.setPassword(userDTO.getPassword());
			}
			if (userDTO.getPhone() != null) {
				entity.setPhone(userDTO.getPhone());
			}
			if (userDTO.getUsername() != null) {
				entity.setUsername(userDTO.getUsername());
			}
			if (userDTO.getUserStatus() != 0L) {
				entity.setUserStatus(userDTO.getUserStatus());
			}
			User savedEntity = userRepository.save(entity);
			return userMapper.toUserDTO(savedEntity);
		} else {
			// TODO: Construct better Response
			throw new RuntimeException("Entity Not Found");
		}
	}

}
