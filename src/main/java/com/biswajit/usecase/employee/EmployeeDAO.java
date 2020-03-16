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
	 * 
	 * @param employee
	 * @return
	 * @throws EmployeeServiceException
	 */
	public String createEmployee(EmployeeTO employee) throws EmployeeServiceException {
		String strMessage = "Failure";
		EmployeeTO checkEmployeeTO = employeeRepository.findByEmailID(employee.getEmailId().trim());
		if (null == checkEmployeeTO) {
			employeeRepository.save(employee);
			strMessage = "Success";
		} else {
			strMessage = "RecordExists";
		}
		return strMessage;
	}

	/**
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