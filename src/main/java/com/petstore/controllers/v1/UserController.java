package com.petstore.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petstore.api.v1.model.UserDTO;
import com.petstore.service.UserService;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
	}

	@GetMapping("/{username}")
	public ResponseEntity<UserDTO> getByUsername(@PathVariable String username) {
		return new ResponseEntity<>(userService.findUserByUserName(username), HttpStatus.OK);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Object> deleteByUsername(@PathVariable String username) {
		userService.deleteUser(username);
		return ResponseEntity.noContent().build();
	}

	// Update User CRUD
	@PutMapping("/{username}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
		return new ResponseEntity<>(userService.updateUser(username, userDTO), HttpStatus.CREATED);
	}

	// Patch API to update User Entity
	@PatchMapping("/{username}")
	public ResponseEntity<UserDTO> patchUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
		return new ResponseEntity<>(userService.patchUser(username, userDTO), HttpStatus.CREATED);
	}

}
