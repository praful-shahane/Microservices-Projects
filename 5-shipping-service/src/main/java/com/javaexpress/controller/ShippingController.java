package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.ShippingRequestDto;
import com.javaexpress.dto.ShippingResponseDto;
import com.javaexpress.service.ShippingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/shipping")
@Slf4j
public class ShippingController {
	
	
	@Autowired
	private ShippingService shippingService;
	
	@PostMapping
    public ShippingResponseDto shipOrder(@RequestBody ShippingRequestDto shippingRequestDto)
    {return shippingService.shipOrder(shippingRequestDto);
    }
	
	
}
