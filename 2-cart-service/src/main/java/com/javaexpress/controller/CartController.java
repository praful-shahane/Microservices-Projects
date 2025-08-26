package com.javaexpress.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.CartItemRequestDto;
import com.javaexpress.dto.CartItemResponseDto;
import com.javaexpress.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	Logger log = LoggerFactory.getLogger(CartController.class);

	
	@Autowired
	private CartService cartService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CartItemResponseDto addItemToCart(@Validated @RequestBody CartItemRequestDto cartItemRequestDto) {
		log.info("CartController :: addItemToCart method cartItemRequestDto{} ",cartItemRequestDto.toString());
		CartItemResponseDto cartItemResponseDto = cartService.addToCart(cartItemRequestDto);
		return cartItemResponseDto;
	}
	
	@GetMapping("/{userId}")
	public List<CartItemResponseDto> getCartByUserId(@PathVariable Long userId) {
		log.info("CartController :: getCartByUserId method userId{} ",userId);
		return cartService.getUserCart(userId);
	}
	@DeleteMapping("/clear/{userId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void clearUserCart(@PathVariable Long userId) {
		log.info("CartController :: clearUserCart method userId{} ",userId);
		cartService.clearCart(userId);
	}
	
	@PutMapping
	public CartItemResponseDto updateQuantity(@RequestBody CartItemRequestDto cartItemRequestDto) {
		log.info("CartController :: updateQuantity method  ");
		return cartService.updateQuantity(cartItemRequestDto);
	}
	
	@DeleteMapping("/remove/{productId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeItemFromCart(@PathVariable Long productId, @RequestParam Long userId) {
		log.info("CartController :: removeItemFromCart method productId {} userId{} ", productId, userId);
		cartService.removeItem(userId, productId);
	}

}
