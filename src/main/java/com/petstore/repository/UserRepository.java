package com.petstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petstore.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
