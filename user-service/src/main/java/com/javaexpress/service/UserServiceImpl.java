package com.javaexpress.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.javaexpress.dto.UserDto;
import com.javaexpress.mappers.UserMapper;
import com.javaexpress.models.Credential;
import com.javaexpress.models.User;
import com.javaexpress.repository.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	

	@Override
	public UserDto save(UserDto userDto) {
		
		//convert dto to entity
		User user = userMapper.toEntity(userDto);
		//TO DO :- we provode orginal password but in DB we save password in encoded form.
		/*
		 * as we know User & Entity both bidectional.
		 * so, we need to set crdential object into user entity & user object into credential Entity.
		 * 
		 */
		Credential credential = user.getCredential();
		credential.setUser(user);
		
		User dbUser = userRepository.save(user);
		return userMapper.toDto(dbUser);
	}

	@Override
	public UserDto findById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto update(Integer userId, UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
