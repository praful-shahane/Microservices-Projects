package com.javaexpress.models;

public enum RoleBasedAuthority {
	
	
	ROLE_USER("USER"),
	ROLE_ADMIN("ADMIN");
	
	private final String role;

	
	private RoleBasedAuthority(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
	
	

}
