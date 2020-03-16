package com.biswajit.usecase.employee.util;

/**
 * The EmployeeServiceException class.
 * 
 * @author biswajit.rout
 *
 */
public class EmployeeServiceException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7390150108191824214L;

	/** The error code. */
	private String errorCode = "Unknown_Exception";

	/**
	 * Instantiates a new backup management exception.
	 *
	 * @param strMessage the str message
	 * @param errorCode  the error code
	 */
	public EmployeeServiceException(String strMessage, String errorCode) {
		super(strMessage);
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return this.errorCode;
	}

	/**
	 * Instantiates a new backup management exception.
	 *
	 * @param e the e
	 */
	public EmployeeServiceException(Throwable e) {
		super(e);
	}

	/**
	 * Instantiates a new backup management exception.
	 *
	 * @param e the e
	 */
	public EmployeeServiceException(ReflectiveOperationException e) {
		super(e);
	}

	/**
	 * Instantiates a new backup management exception.
	 *
	 * @param strMessage the str message
	 */
	public EmployeeServiceException(String strMessage) {
		super(strMessage);
	}
}
