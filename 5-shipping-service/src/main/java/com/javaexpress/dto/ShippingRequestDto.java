package com.javaexpress.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShippingRequestDto {
	
	private Long orderId;
	private String shippingMethod; //STANDARD, EXPRESS
	private String carrier; //FEDEX,Shadow
	
	

}
