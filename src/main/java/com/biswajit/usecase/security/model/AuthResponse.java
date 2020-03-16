package com.biswajit.usecase.security.model;

import java.io.Serializable;

/**
 * 
 * @author biswajit.rout
 *
 */
public class AuthResponse implements Serializable {

	private static final long serialVersionUID = -717330553651674609L;

	public AuthResponse(String jwtToken) {
		this.token = jwtToken;
	}

	public String getToken() {
		return this.token;
	}

	private final String token;
}