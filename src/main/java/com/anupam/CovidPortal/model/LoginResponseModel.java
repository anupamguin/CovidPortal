package com.anupam.CovidPortal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LoginResponseModel {

	private boolean success;
	private String token;
	
	public LoginResponseModel(boolean success, String token) {
		super();
		this.success = success;
		this.token = token;
	}

	public LoginResponseModel(boolean success) {
		super();
		this.success = success;
	}
	
	public LoginResponseModel() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
	
}
