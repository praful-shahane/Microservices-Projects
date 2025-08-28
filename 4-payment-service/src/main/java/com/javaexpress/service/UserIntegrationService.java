package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.PaymentRequestDTO;
import com.javaexpress.dto.UserDto;
import com.javaexpress.feignclient.UserFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

/*
 * we are calling user-service api.
 * if we write this same method implementation in paymentServiceImpl
 *  our logic of circuit breaker will not work & 
 *  it will break S-single responsibilty principle.
 *  so, we create seperate method of user-service api call.
 */
@Service
@Slf4j
public class UserIntegrationService {
	
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	
	/*
	 * This method using we call user-service
	 */
	@CircuitBreaker(name = "userServiceCB",fallbackMethod = "userFallback")
	public UserDto fetchUser(PaymentRequestDTO paymentRequestDTO) {
		UserDto userDto = userFeignClient.fetchUser(paymentRequestDTO.getUserId().intValue());
		if(userDto ==null) {
			throw new RuntimeException("USER NOT FOUND IN DB");
		}
		return userDto;
	}
	
	private UserDto userFallback(PaymentRequestDTO paymentRequestDTO, Throwable t) {
		log.error("User Service fallback {}",t.getMessage());
		throw new RuntimeException("USER SERVICE IS UNAVAILABLE.  PLEASE TRY AGAIN LATER");
	}
	

}
