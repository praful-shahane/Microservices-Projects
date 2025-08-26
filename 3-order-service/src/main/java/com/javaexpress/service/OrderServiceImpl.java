package com.javaexpress.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CartItemResponseDto;
import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.dto.PlacedOrderRequestDto;
import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.dto.UserDto;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.feignclients.CartFeignClient;
import com.javaexpress.feignclients.ProductFeignClient;
import com.javaexpress.feignclients.UserFeignClient;
import com.javaexpress.repository.OrderRepository;
import com.netflix.discovery.provider.Serializer;

@Service
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private  OrderRepository orderRepository;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private CartFeignClient cartFeignClient;
	
	@Autowired
	private ProductFeignClient productFeignClient;

	 
	

	@Override
	public OrderResponseDto placeOrder(PlacedOrderRequestDto placedOrderRequestDto) {
		
		//STEP 1=>validate user.
		UserDto  userDto = validateUser(placedOrderRequestDto.getUserId());
		if(userDto == null) {
			throw new ResourceNotFoundException("User Not found in DB");
		}
		
		//STEP 2=>get cart items.
		List<CartItemResponseDto> cartItems = fetchCartItems(placedOrderRequestDto.getUserId());
		if(cartItems == null || cartItems.isEmpty()) {
			throw new ResourceNotFoundException("CART IS EMPTY...");
		}
		
		//STEP 3=>calculate total price.
		BigDecimal totalPrice = calculateTotalPrice(cartItems);
		
		//Build Cart Items
		buildCartItems(cartItems);
		
		
		return null;
	}


	private void buildCartItems(List<CartItemResponseDto> cartItems) {
		
		
	}


	private BigDecimal calculateTotalPrice(List<CartItemResponseDto> cartItems) {
	  BigDecimal total= BigDecimal.ZERO ;
		for (CartItemResponseDto item : cartItems) {
			//get product object
			ProductResponseDTO product = productFeignClient.getProduct(item.getProductId());
			//get quantity from item
			Integer quantity = item.getQuantity();
			
			BigDecimal individualPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
			total = total.add(individualPrice);
			}
		return total;
		
	}


	private List<CartItemResponseDto> fetchCartItems(Long userId) {
		return cartFeignClient.getCartByUserId(userId);
		
	}


	private UserDto validateUser(Long userId) {
		return userFeignClient.fetchUser(userId.intValue());
	}

}
