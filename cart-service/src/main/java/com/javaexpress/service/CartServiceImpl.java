package com.javaexpress.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.controller.CartController;
import com.javaexpress.dto.CartItemRequestDto;
import com.javaexpress.dto.CartItemResponseDto;
import com.javaexpress.models.CartItem;
import com.javaexpress.repository.CartItemRepository;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {
	
	Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public CartItemResponseDto addToCart(CartItemRequestDto cartItemRequestDto) {
		log.info("CartServiceImpl :: addToCart method  ");
		//TODO:: product service api need to call to fetch product information
		//we can use RESTTemplate & hardcode the url.
		//but we can do it by using eureka server or feignclient.
		
		//TODO:: user service api need to call to fetch user details.
		
		
		/*
		 * if above both api call success i will save my cartItem into DB.
		 */
		
		CartItem cartItem = new CartItem();
		BeanUtils.copyProperties(cartItemRequestDto, cartItem);
		CartItem dbCartItem = cartItemRepository.save(cartItem);
		return mapToResponseDto(dbCartItem);
	}

	private CartItemResponseDto mapToResponseDto(CartItem dbCartItem) {
		
		CartItemResponseDto responseDto = new CartItemResponseDto();
		BeanUtils.copyProperties(dbCartItem, responseDto);
		return responseDto;
	}

	@Override
	public List<CartItemResponseDto> getUserCart(Long userId) {
		log.info("CartServiceImpl :: getUserCart method  ");
		List<CartItemResponseDto> responseDtoList = cartItemRepository.findByUserId(userId)
				.stream().map(e-> mapToResponseDto(e)).toList();
		return responseDtoList;
	}

	@Override
	@Transactional
	public void removeItem(Long userId, Long productId) {
		/*
		 * save() method is deafult method it use @transaction automatically.
		 * but deleteByUserIdAndProductId () it is custome method we define it.
		 * so we need to handle DDL(Manipulation operation) operation we use @Transaction.
		 * if we not use @transaction on custom method, we get exception try once. 
		 * Whenever we are performing delete operation use @transactional annotation.
		 */
		log.info("CartServiceImpl :: removeItem method  ");
		cartItemRepository.deleteByUserIdAndProductId(userId, productId);
	}

	@Override
	public CartItemResponseDto updateQuantity(CartItemRequestDto cartItemRequestDto) {
                 /*
                  * to update quantity, we need productId & userId.
                  */
		CartItem cartItem = cartItemRepository.findByUserIdAndProductId(cartItemRequestDto.getUserId(), cartItemRequestDto.getProductId())
		.orElseThrow(()-> new RuntimeException("Item not in the cart"));
		cartItem.setQuantity(cartItemRequestDto.getQuantity());
		CartItem dbcartItem = cartItemRepository.save(cartItem);
		return mapToResponseDto(dbcartItem);
	}

	@Override
	@Transactional
	public void clearCart(Long userId) {
		log.info("CartServiceImpl :: clearCart method  ");
		cartItemRepository.deleteByUserId(userId);
      }

}
