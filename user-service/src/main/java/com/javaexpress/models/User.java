package com.javaexpress.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	
	
	/*
	 * one user has only one crdentials.so we map @oneToOne.
	 */
	/*
	 * 2)credentials entity owns the relationship & the user field in it holds the foreign key.
	 * 3)at one shot i want to store userData & credentials data, it is called cascading.
	 * cascade = CascadeType.ALL all operations will be performed with child & parent.
	 */
	
	/*
	 * Eager Loading vs Lazy Loading
	 * ================================
	 * Eager Loading =>when parent data get, child data is loaded immediately.
	 * in Lazy Loading => when parent data get, child data is not loaded immediately 
	 * so, we use  fetch = FetchType.LAZY
	 * by deafult it is Eager loading.
	 * 
	 */
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Credential credential;


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Credential getCredential() {
		return credential;
	}


	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	

	
	
	
	
	
}
