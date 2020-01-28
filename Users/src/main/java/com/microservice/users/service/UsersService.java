package com.microservice.users.service;

import com.microservice.users.shared.UserDto;

public interface UsersService {

	UserDto createUser(UserDto userDetails);
	
}
