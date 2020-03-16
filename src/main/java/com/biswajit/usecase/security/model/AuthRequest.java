package com.biswajit.usecase.security.model;

import java.io.Serializable;

/**
 * 
 * @author biswajit.rout
 *
 */
public class AuthRequest implements Serializable {

	private static final long serialVersionUID = 3074970146887555594L;

	// Need default constructor for JSON Parsing
	public AuthRequest() {

	}

	public AuthRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	private String username;

	private String password;
}