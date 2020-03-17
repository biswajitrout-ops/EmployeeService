package com.biswajit.usecase.employee.util;

import java.io.Serializable;

/**
 * The Message class.
 * 
 * @author biswajit.rout
 */
public class Message implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6070659002140619998L;

	/**
	 * Instantiates a new responseMessage.
	 */
	public Message() {
		super();
	}

	/**
	 * Instantiates a new responseMessage.
	 *
	 * @param code    the code
	 * @param message the message
	 */
	public Message(String code, String message) {
		super();
		this.responseCode = code;
		this.responseMessage = message;
	}

	/**
	 * Gets the response code.
	 *
	 * @return the response code
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the response code.
	 *
	 * @param responseCode the new response code
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Gets the response message.
	 *
	 * @return the response message
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * Sets the response message.
	 *
	 * @param responseMessage the new response message
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/** The response code. */
	private String responseCode = null;

	/** The response message. */
	private String responseMessage = null;
}
