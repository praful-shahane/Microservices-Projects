package com.javaexpress.service;

import com.javaexpress.dto.UserDto;
import com.javaexpress.models.User;

public interface UserService {
	
	UserDto save(UserDto userDto);
	
	UserDto findById(Integer userId);
	
	UserDto  update(Integer userId, UserDto  userDto);
	 

}
