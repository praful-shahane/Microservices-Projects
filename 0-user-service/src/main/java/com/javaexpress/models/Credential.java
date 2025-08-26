package com.javaexpress.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Credential {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer crdentialId;
	
	@Column(unique = true) //username must be unique
	private String username;
	private String password;
	
	
	/*
	 * if we not use @Enumerated, our data for roleBasedAuthority column will be stored
	 * as number in database.
	 * but we want to store a string data exact name, we should use @Enumerated(EnumType.STRING)
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RoleBasedAuthority roleBasedAuthority;
	
	/*
	 * whenever there is a bidrectional, it may lead to recursion lead to stackOverflow.
	 * bcz user is fetching credential data & vice versa.
	 * 
	 * when we fetch User data, we get crdential data also.
	 * but when we fetch crdential data based on username, we might not need user data.
	 * so we can ignore that data, by using @JsonIgnore.
	 * @JsonIgnore it prevent infinite loop.
	 * 
	 * this above is applicable only in bidrectional case.
	 * 
	 */
	
	@JsonIgnore //tells jackson library not to include this field in JSON output.
	@OneToOne(fetch = FetchType.LAZY) //one credentails belongs to one user only.
	@JoinColumn(name="user_id", unique = true) //we make user_id is unique it means it must not be null & unique.
	private User user;

	
	public Credential() {
		// TODO Auto-generated constructor stub
	}


	public Integer getCrdentialId() {
		return crdentialId;
	}


	public void setCrdentialId(Integer crdentialId) {
		this.crdentialId = crdentialId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public RoleBasedAuthority getRoleBasedAuthority() {
		return roleBasedAuthority;
	}


	public void setRoleBasedAuthority(RoleBasedAuthority roleBasedAuthority) {
		this.roleBasedAuthority = roleBasedAuthority;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
}
