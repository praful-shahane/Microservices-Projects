package com.javaexpress.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemRequestDto {

	private Long userId;
	private Long productId;
	private Integer quantity;
}
