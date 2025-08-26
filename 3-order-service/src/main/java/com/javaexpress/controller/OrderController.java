package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.dto.PlacedOrderRequestDto;
import com.javaexpress.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping
	public OrderResponseDto placeOrder(@RequestBody PlacedOrderRequestDto placedOrderRequestDto) {
		log.info("OrderController placeOrder {} ",placedOrderRequestDto.getUserId());
	  return orderService.placeOrder(placedOrderRequestDto);	
	}
	
	@PutMapping("/{orderId}/status/{status}")
	public void updateOrderStatus(@PathVariable Long orderId , @PathVariable String status) {
		log.info("OrderController updateOrderStatus ");
		orderService.updateOrderStatus(orderId, status);
	}
	
	
    @GetMapping("/{orderId}")
	public void getOrder(@PathVariable Long orderId) {
		log.info("OrderController getOrder ");
		orderService.getOrderById(orderId);
	}
	
	
}
