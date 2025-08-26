package com.javaexpress.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.controller.UserController;
import com.javaexpress.dto.CredentialDto;
import com.javaexpress.mappers.CredentialMapper;
import com.javaexpress.repository.CredentialRepository;

@Service
public class CredentialService {
	
	 Logger log = LoggerFactory.getLogger(CredentialService.class);
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	@Autowired
	private CredentialMapper credentialMapper;
	
	
	public CredentialDto findByUserName(String username) {
		log.info("CredentialService  :: findByUserName {}",username);
		
		return credentialRepository.findByUsername(username)
				.map(credentialMapper ::toDto)
				.orElseThrow(()-> new RuntimeException("No data for given username is available"));
	}
	
	public CredentialDto findByUserNameAndPassword(String username, String password) {
		log.info("CredentialService  :: findByUserNameAndPassword {}",username,password);
		return credentialRepository.findByUsernameAndPassword(username,password)
				.map(credentialMapper ::toDto)
				.orElseThrow(()-> new RuntimeException("No data for given username is available"));
	}
	
	
	

}
