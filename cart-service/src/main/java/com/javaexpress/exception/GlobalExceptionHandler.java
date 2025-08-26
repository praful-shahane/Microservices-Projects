package com.javaexpress.exception;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Logger log =  LoggerFactory.getLogger(GlobalExceptionHandler.class);;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorAPI> handleResourceNotFoundException(ResourceNotFoundException exs){
		log.error("Resource Not found Exception.....{}",exs.getMessage());
		
		 ErrorAPI errorAPI = new ErrorAPI();
		 errorAPI.setLocalDateTime(LocalDateTime.now());
		 errorAPI.setMessage(exs.getMessage());
		 errorAPI.setError("Validation Error");
		 errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		 
		 return new ResponseEntity<ErrorAPI>(errorAPI, HttpStatus.BAD_REQUEST);
		
	}
	
	
	

}
