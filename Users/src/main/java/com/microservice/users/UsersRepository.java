package com.microservice.users;

import org.springframework.data.repository.CrudRepository;

import com.microservice.users.data.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
//	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
