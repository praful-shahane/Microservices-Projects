package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {

}
