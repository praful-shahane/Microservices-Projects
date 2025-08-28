package com.javaexpress.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.PaymentRequestDTO;
import com.javaexpress.dto.PaymentResponseDTO;
import com.javaexpress.dto.UserDto;
import com.javaexpress.feignclient.UserFeignClient;
import com.javaexpress.models.Payment;
import com.javaexpress.repository.PaymentRepository;
import com.netflix.discovery.converters.Auto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
	
	
	@Autowired
	private PaymentRepository paymentRepository;

	
	@Autowired
	private UserIntegrationService   userIntegrationService; //Inject  user service
	
	

	@Override
	public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO) {

		//TO DO  :: assignment for order commuinication. find orderdetails by orderid.
		
		//TO DO :: assignment for user communication.
		UserDto userDto = userIntegrationService.fetchUser(paymentRequestDTO); //will trigger circuitbreaker
		
		Payment payment = new Payment();
		BeanUtils.copyProperties(paymentRequestDTO, payment);
		payment.setStatus("SUCCESS");
		paymentRepository.save(payment);
		
		return mapToDTO(payment);
	}

	
	

	private PaymentResponseDTO mapToDTO(Payment payment) {
		
		 PaymentResponseDTO responseDTO = new PaymentResponseDTO();
		 BeanUtils.copyProperties(payment, responseDTO);
		 responseDTO.setPaymentId(payment.getId());
		return responseDTO;
	}

}
