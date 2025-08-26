package com.javaexpress.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShippingResponseDto {
	
	private Long orderId;
	private String shippingMethod; //STANDARD, EXPRESS
	private String carrier; //FEDEX,Shadow
	private String status;
	private LocalDateTime shippedAt;
	private LocalDateTime deliveryDate;
	
	
	
	

}
