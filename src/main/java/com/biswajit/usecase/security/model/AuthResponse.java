package com.biswajit.usecase.security.model;

import java.io.Serializable;

/**
 * 
 * @author biswajit.rout
 *
 */
public class AuthResponse implements Serializable {

	private static final long serialVersionUID = 4592308383661198479L;

	private final String token;

	public AuthResponse(String jwttoken) {
		this.token = jwttoken;
	}

	public String getToken() {
		return this.token;
	}
}