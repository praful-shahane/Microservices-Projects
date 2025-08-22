package com.javaexpress.service;

import java.util.List;

import com.javaexpress.dto.CartItemRequestDto;
import com.javaexpress.dto.CartItemResponseDto;
import com.javaexpress.models.CartItem;

public interface CartService {
	
	//to add product into cart-service it is POST API.
	CartItemResponseDto addToCart(CartItemRequestDto cartItemRequestDto);
	
	/*
	 * when user click on Cart button, 
	 * user you will able to 
	 * see list of product item which he added into cart.
	 */
	
	  List<CartItemResponseDto>  getUserCart(Long userId);

	  
	  /*
	   * we can remove item from cart as well.
	   */
	  
	   void removeItem(Long userId,Long productId);
	   
		  /*
		   * update quantity of item/product from cart
		   */
	   
	  CartItemResponseDto updateQuantity(CartItemRequestDto cartItemRequestDto);
	  
	  /*
	   * remove all items from cart
	   */
	     void clearCart(Long userId);
	   
		  
}
