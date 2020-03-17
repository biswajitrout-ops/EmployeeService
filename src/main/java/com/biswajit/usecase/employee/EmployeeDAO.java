package com.biswajit.usecase.employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.biswajit.usecase.employee.model.EmployeeTO;
import com.biswajit.usecase.employee.model.EmployeeVO;
import com.biswajit.usecase.employee.util.EmployeeServiceException;

/**
 * 
 * @author biswajit.rout
 *
 */
@Repository("employeeComponent")
public class EmployeeDAO {

	private @Autowired @Lazy(value = true) EmployeeRepository employeeRepository;

	/**
	 * This method creates an employee record
	 * 
	 * @param employeeData
	 * @return
	 * @throws EmployeeServiceException
	 */
	public String createEmployee(EmployeeTO employeeData) throws EmployeeServiceException {
		String strMessage = "Failure";
		EmployeeTO checkEmployee = employeeRepository.findByEmailID(employeeData.getEmailId().trim());
		if (null == checkEmployee) {
			employeeRepository.save(employeeData);
			strMessage = "Success";
		} else {
			strMessage = "RecordExists";
		}
		return strMessage;
	}

	/**
	 * This method retrieves list of employee details based on company name
	 * 
	 * @param companyName
	 * @return
	 * @throws EmployeeServiceException
	 */
	public List<EmployeeVO> getEmployeeByCompanyName(String companyName) throws EmployeeServiceException {
		List<EmployeeTO> employeeList = employeeRepository.findByCompanyName(companyName);
		List<EmployeeVO> respEmployeeList = null;

		if (isNotEmpty(employeeList)) {
			respEmployeeList = new ArrayList<>(employeeList.size());
			for (EmployeeTO employeeTO : employeeList) {
				EmployeeVO employeeVO = new EmployeeVO();
				BeanUtils.copyProperties(employeeTO, employeeVO);
				respEmployeeList.add(employeeVO);
			}
		} else {
			respEmployeeList = new ArrayList<>();
		}
		return respEmployeeList;
	}

	/**
	 * This method retrieves employees details based on employee ID
	 * 
	 * @param employeeID
	 * @return
	 * @throws EmployeeServiceException
	 */
	public EmployeeVO getEmployeeByID(String employeeID) throws EmployeeServiceException {
		EmployeeTO employee = employeeRepository.findByEmployeeID(employeeID);
		if (null != employee) {
			EmployeeVO employeeVO = new EmployeeVO();
			BeanUtils.copyProperties(employee, employeeVO);
			return employeeVO;
		} else {
			return null;
		}
	}

	/**
	 * This method updates an employee record based on employee ID and other input
	 * 
	 * @param employeeID
	 * @param employeeData
	 * @return
	 * @throws EmployeeServiceException
	 */
	public String updateEmployee(String employeeID, EmployeeTO employeeData) throws EmployeeServiceException {
		String strMessage = "Failure";
		EmployeeTO checkEmployee = employeeRepository.findByEmployeeID(employeeID.trim());
		if (null != checkEmployee) {
			checkEmployee.setFirstName(employeeData.getFirstName());
			checkEmployee.setLastName(employeeData.getLastName());
			checkEmployee.setEmailId(employeeData.getEmailId());
			checkEmployee.setCompanyName(employeeData.getCompanyName());
			employeeRepository.save(checkEmployee);
			strMessage = "Success";
		} else {
			strMessage = "NoRecordExists";
		}
		return strMessage;
	}

	/**
	 * This method deletes an employee record based on employee ID
	 * 
	 * @param employeeID
	 * @return
	 * @throws EmployeeServiceException
	 */
	public String deleteEmployee(String employeeID) throws EmployeeServiceException {
		String strMessage = "Failure";
		EmployeeTO checkEmployeeTO = employeeRepository.findByEmployeeID(employeeID.trim());
		if (null != checkEmployeeTO) {
			employeeRepository.delete(checkEmployeeTO);
			strMessage = "Success";
		} else {
			strMessage = "NoRecordExists";
		}
		return strMessage;
	}

	/**
	 * 
	 * @param <E>
	 * @param c
	 * @return
	 */
	private <E> boolean isNotEmpty(Collection<E> c) {
		return !isEmpty(c);
	}

	/**
	 * 
	 * @param <E>
	 * @param c
	 * @return
	 */
	private <E> boolean isEmpty(Collection<E> c) {
		return (c == null || c.isEmpty());
	}
}