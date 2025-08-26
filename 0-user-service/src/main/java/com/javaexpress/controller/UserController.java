package com.javaexpress.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.UserDto;
import com.javaexpress.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
 Logger log = LoggerFactory.getLogger(UserController.class);
                

	@Autowired
	private UserService userService;
	
	@PostMapping
	@ResponseStatus(code =  HttpStatus.CREATED) //201
	public UserDto createUser( @Valid @RequestBody UserDto userDto ) {
		  log.info("UserController  :: createUser {}",userDto.getEmailAddress());
		return userService.save(userDto);
	}

	@GetMapping("/{userId}")
	public UserDto fetchUser(@PathVariable Integer userId) {
		  log.info("UserController  :: fetchUser {}",userId);
		  UserDto userDto = userService.findById(userId);
		return  userDto;
	}
	
	@PutMapping("/{userId}")
	public UserDto  updateUser(@PathVariable Integer userId,@Valid @RequestBody UserDto userDto ) {
		log.info("UserController  :: updateUser method input userDTO is  {}",userDto);
		UserDto updatedUserDTO = userService.update(userId, userDto);
		log.info("UserController  :: updated User DTO is {}",userDto);
		return updatedUserDTO;
	}
	
	@DeleteMapping("/{userId}")
	public String deleteByUser(@PathVariable Integer userId) {
		log.info("UserController  :: deleteByUser method input userId is  {}",userId);
		boolean isDeleted = userService.deleteById(userId);
		if(isDeleted) {
			return "User Object data is deleted successfully";
		}else {
			return "User Object data is not  deleted successfully";
		}
	}

}
