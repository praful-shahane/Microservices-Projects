package com.javaexpress.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.UserDto;

@FeignClient(name="USER-SERVICE",path = "/api/users")
@LoadBalancerClient  //if we have multiple instance of same application, we use @LoadBalancerClient  to balance the request.
public interface UserFeignClient {
	
	@GetMapping("/{userId}")
	public UserDto fetchUser(@PathVariable("userId") Integer userId);

}
