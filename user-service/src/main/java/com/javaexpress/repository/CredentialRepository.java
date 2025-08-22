package com.javaexpress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {

	
	//Method Name
	//Input is username & output is Crdential Object.
	
	Optional<Credential> findByUsername(String username);
	
	Optional<Credential> findByUsernameAndPassword(String username, String password);
}
