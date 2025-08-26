package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.PaymentRequestDTO;
import com.javaexpress.dto.PaymentResponseDTO;
import com.javaexpress.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/payment")
@Slf4j
public class PaymentController {
	
	
	@Autowired
	private PaymentService paymentService;
	
	
	@PostMapping
	public PaymentResponseDTO makePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
		log.info("PaymentController :: makePayment {}",paymentRequestDTO);
		return paymentService.processPayment(paymentRequestDTO);
	}

	
	
	
}
