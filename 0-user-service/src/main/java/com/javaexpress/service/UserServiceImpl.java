package com.javaexpress.service;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.controller.UserController;
import com.javaexpress.dto.UserDto;
import com.javaexpress.mappers.UserMapper;
import com.javaexpress.models.Credential;
import com.javaexpress.models.User;
import com.javaexpress.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	 Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
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
		 
		return userRepository.findById(userId).map(userMapper :: toDto)
				.orElseThrow(()-> new RuntimeException("User not found in DB"));
				
	}

	@Override
	public UserDto update(Integer userId, UserDto userDto) {
		
		log.info(" UserServiceImpl ::  update userId{} userDto{}",userId , userDto);
		Optional<User> userDB = userRepository.findById(userId);
		if(userDB.isPresent()) {
			log.info(" UserServiceImpl ::  user  object is present in DB userDB{}" , userDB.get());
			User userEntity = userDB.get();

			userEntity.setFirstName(userDto.getFirstName());
			userEntity.setLastName(userDto.getLastName());
			userEntity.setPhone(userDto.getContact());
			userEntity.setEmail(userDto.getEmailAddress());
			if(userDto.getCredential() !=null) {
				userEntity.getCredential().setUsername(userDto.getCredential().getUsername());
				userEntity.getCredential().setPassword(userDto.getCredential().getPassword());
				
			}
			return userMapper.toDto(userEntity);
		}else {
			throw new RuntimeException("No User Object is available to update...");
		}
	}

	@Override
	public boolean deleteById(Integer userId) {
		
		userRepository.deleteById(userId);
		return true;
	}

}
