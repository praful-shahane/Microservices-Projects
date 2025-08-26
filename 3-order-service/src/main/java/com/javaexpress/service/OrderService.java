package com.javaexpress.service;

import java.util.List;

import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.dto.PlacedOrderRequestDto;

public interface OrderService {
	
	
	OrderResponseDto placeOrder(PlacedOrderRequestDto placedOrderRequestDto);
	
	void updateOrderStatus(Long orderId, String status);
	
	 List<OrderResponseDto> getOrdersByUser(Long userId);
	

}
