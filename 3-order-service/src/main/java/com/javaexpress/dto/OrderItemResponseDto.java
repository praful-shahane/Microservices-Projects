package com.javaexpress.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemResponseDto {
	
	private Long productId;
	private Integer quantity;
	private BigDecimal price;
	

}
