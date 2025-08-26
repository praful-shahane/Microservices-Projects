package com.javaexpress.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.javaexpress.dto.CartItemResponseDto;

@FeignClient(name = "CART-SERVICE",path = "/api/cart") 
public interface CartFeignClient {
	
	@GetMapping("/{userId}")
	public List<CartItemResponseDto> getCartByUserId(@PathVariable("userId") Long userId) ;
	
	@DeleteMapping("/clear/{userId}")
	public void clearUserCart(@PathVariable("userId") Long userId) ;

}
