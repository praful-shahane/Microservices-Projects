package com.javaexpress.service;

import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.dto.PlacedOrderRequestDto;

public interface OrderService {
	
	
	OrderResponseDto placeOrder(PlacedOrderRequestDto placedOrderRequestDto);
	

}
