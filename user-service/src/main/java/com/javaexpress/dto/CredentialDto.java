package com.javaexpress.dto;

import com.javaexpress.models.RoleBasedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CredentialDto {

	 
	private String username;
	private String password;
	private RoleBasedAuthority roleBasedAuthority;
	
	
	
}
