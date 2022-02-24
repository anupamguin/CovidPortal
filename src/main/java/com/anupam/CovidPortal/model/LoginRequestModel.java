package com.anupam.CovidPortal.model;

import javax.validation.constraints.NotBlank;

public class LoginRequestModel {

	@NotBlank(message = "Username must not be Blank")
	private String username;
	
	@NotBlank(message = "Password must not be Blank")
	private String password;
	
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
	@Override
	public String toString() {
		return "LoginModel [username=" + username + ", password=" + password + "]";
	}
	
	
}
