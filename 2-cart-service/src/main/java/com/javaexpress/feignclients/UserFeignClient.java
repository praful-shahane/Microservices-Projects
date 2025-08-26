package com.javaexpress.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.UserDto;


/*
 * @FeignClient(name = "product",url="http://localhost:8051",path = "/api/users") =>only for one instance of application.
 * 
 * if we have multiple instance of same application runing, we not able to use above.
 * but we can use 
 * @FeignClient(name="USER-SERVICE",path = "/api/users")
 */
@FeignClient(name="USER-SERVICE",path = "/api/users")
@LoadBalancerClient  //if we have multiple instance of same application, we use @LoadBalancerClient  to balance the request.
public interface UserFeignClient {
	
	@GetMapping("/{userId}")
	public UserDto fetchUser(@PathVariable("userId") Integer userId);

}
