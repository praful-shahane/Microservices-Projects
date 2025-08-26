package com.javaexpress.service;

import com.javaexpress.dto.ShippingRequestDto;
import com.javaexpress.dto.ShippingResponseDto;

public interface ShippingService {
	
	ShippingResponseDto  shipOrder( ShippingRequestDto shippingRequestDto);
	
	void updateShippingStatus(Long orderId,String status);

}
