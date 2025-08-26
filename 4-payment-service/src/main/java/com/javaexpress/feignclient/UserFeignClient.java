package com.javaexpress.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.UserDto;

@FeignClient(name = "USER-SERVICE",path = "/api/users")
public interface UserFeignClient {
	
	@GetMapping("/{userId}")
	public UserDto fetchUser(@PathVariable Integer userId);

}
