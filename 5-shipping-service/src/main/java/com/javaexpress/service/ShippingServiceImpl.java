package com.javaexpress.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.ShippingRequestDto;
import com.javaexpress.dto.ShippingResponseDto;
import com.javaexpress.models.ShippingInfo;
import com.javaexpress.repository.ShippingRepository;

@Service
public class ShippingServiceImpl implements ShippingService {
	
	
	@Autowired
	private ShippingRepository shippingRepository;

	
	
	@Override
	public ShippingResponseDto shipOrder(ShippingRequestDto shippingRequestDto) {
		
		//TO DO :: Assignment for order communication
		
		ShippingInfo shippingInfo = new ShippingInfo();
		BeanUtils.copyProperties(shippingRequestDto, shippingInfo);
		shippingInfo.setStatus("SHIPPED");
		
		ShippingInfo dbShipping = shippingRepository.save(shippingInfo);
		
		//TO DO :: Assignment UPDATE order status to SHIPPED in Order table to Shipped
		
		return mapToDTO(dbShipping);
	}



	private ShippingResponseDto mapToDTO(ShippingInfo dbShipping) {
	   
		ShippingResponseDto responseDto = new ShippingResponseDto();
		BeanUtils.copyProperties(dbShipping, responseDto);
		responseDto.setShippedAt(LocalDateTime.now());
		return responseDto;
	}



	@Override
	public void updateShippingStatus(Long orderId, String status) {
	
		ShippingInfo shippingInfo = shippingRepository.findByOrderId(orderId);
		if(shippingInfo==null) {
			throw new RuntimeException("ORDER NOT FOUND");
		}
		shippingInfo.setStatus(status);
		shippingInfo.setDeliveryDate("DELIVERED".equals(status) ? LocalDateTime.now() : null);
		shippingRepository.save(shippingInfo);
		
		if("DELIVERED".equals(status)) {
			//UPDATE the order status from SHIPPED to  DELIVERED in order table.
		}
		
	}

}
