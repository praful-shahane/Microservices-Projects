package com.javaexpress.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.CredentialDto;
import com.javaexpress.service.CredentialService;

@RestController
@RequestMapping("/api/credentials")
public class CredentialController {
	
	Logger log = LoggerFactory.getLogger(CredentialController.class);
	
	@Autowired
	private CredentialService credentialService;
	
	@GetMapping("/username/{uname}")
	public CredentialDto findByUsername(@PathVariable("uname") String  userName) {
		log.info(" CredentialController :: findByUsername");
		 return credentialService.findByUserName(userName);
	}
	
	

}
