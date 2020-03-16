package com.biswajit.usecase.employee.model;

import static com.biswajit.usecase.employee.util.EmployeeConstants.EMPLOYEE_CN_MANDATORY;
import static com.biswajit.usecase.employee.util.EmployeeConstants.EMPLOYEE_CN_SIZE;
import static com.biswajit.usecase.employee.util.EmployeeConstants.EMPLOYEE_EMAIL_MANDATORY;
import static com.biswajit.usecase.employee.util.EmployeeConstants.EMPLOYEE_FN_MANDATORY;
import static com.biswajit.usecase.employee.util.EmployeeConstants.EMPLOYEE_FN_SIZE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 
 * @author biswajit.rout
 *
 */
@Entity
@Table(name = "employees")
public class EmployeeTO implements Serializable {

	private static final long serialVersionUID = -5146000683172949428L;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Id
	private String id;

	@NotEmpty(message = EMPLOYEE_FN_MANDATORY)
	@Size(min = 3, max = 30, message = EMPLOYEE_FN_SIZE)
	private String firstName;

	private String lastName;

	@NotEmpty(message = EMPLOYEE_EMAIL_MANDATORY)
	@Email
	private String emailId;

	@NotEmpty(message = EMPLOYEE_CN_MANDATORY)
	@Size(min = 2, max = 25, message = EMPLOYEE_CN_SIZE)
	private String companyName;

}