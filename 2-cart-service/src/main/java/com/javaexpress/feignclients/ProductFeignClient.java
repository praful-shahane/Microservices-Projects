package com.javaexpress.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/*  this name value it is get from product-service project application.properties file.
  ex:-
  property we specify in user-service.
  but in EUREKA it will store this in all caps letter as "USER-SERVICE"
  spring.application.name=user-service   
  if we are using EUREKA SERVER, then no need to use url for this.
  but if we not use EUREKA SERVER, we need to add url & value of url be.
  ex:-
  @FeignClient(name = "PRODUCT-SERVICE", url = "http://localhost:8052") */
 
@FeignClient(name = "PRODUCT-SERVICE",path = "/api/products") 
public interface ProductFeignClient{
	
	//i need to check productId is exist or not in cart service.
	//so, i copy method from product-service & paste in ProductFeignClient interface in cart-service.
	@GetMapping("/exists/{productId}")
	public boolean isProductExists(@PathVariable Long productId);

}
