package com.biswajit.usecase.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.biswajit.usecase.employee.model.EmployeeTO;
import com.biswajit.usecase.employee.model.EmployeeVO;
import com.biswajit.usecase.employee.util.EmployeeServiceException;

/**
 * 
 * @author biswajit.rout
 *
 */
@Service("employeeService")
public class EmployeeService {

	private @Autowired @Lazy(value = true) EmployeeDAO employeeComponent;

	/**
	 * 
	 * @param employee
	 * @return
	 * @throws EmployeeServiceException
	 */
	public EmployeeVO createEmployee(EmployeeTO employee) throws EmployeeServiceException {
		String message = employeeComponent.createEmployee(employee);
		if ("Success".equalsIgnoreCase(message)) {
			return constructDynamicMessages("200", "The Employee record created Successfully");
		} else if ("RecordExists".equalsIgnoreCase(message)) {
			return constructDynamicMessages("405", "The Employee record already exists");
		} else
			return null;
	}

	/**
	 * 
	 * @param companyName
	 * @return
	 * @throws EmployeeServiceException
	 */
	public List<EmployeeVO> getEmployeeByCompanyName(String companyName) throws EmployeeServiceException {
		List<EmployeeVO> employeeList = employeeComponent.getEmployeeByCompanyName(companyName);
		if (employeeList.size() > 0) {
			return employeeList;
		} else {
			EmployeeVO empVO = constructDynamicMessages("410",
					"Not a single Employee record exist for the given Company Name");
			return constructDynamicMessages(empVO);
		}
	}

	/**
	 * 
	 * @param employeeID
	 * @return
	 * @throws EmployeeServiceException
	 */
	public EmployeeVO getEmployeeByID(String employeeID) throws EmployeeServiceException {
		EmployeeVO employeeList = employeeComponent.getEmployeeByID(employeeID);
		if (null != employeeList) {
			return employeeList;
		} else {
			return constructDynamicMessages("415", "Not a single Employee record exist for the given Employee ID");
		}
	}

	/**
	 * 
	 * @param code
	 * @param strMessage
	 * @return
	 */
	private EmployeeVO constructDynamicMessages(String code, String strMessage) {
		EmployeeVO responseEmployeeVO = new EmployeeVO();
		responseEmployeeVO.setResponseCode(code);
		responseEmployeeVO.setResponseMessage(strMessage);
		return responseEmployeeVO;
	}

	/**
	 * 
	 * @param employeeVO
	 * @return
	 */
	private List<EmployeeVO> constructDynamicMessages(EmployeeVO employeeVO) {
		List<EmployeeVO> responseList = new ArrayList<>(1);
		responseList.add(employeeVO);
		return responseList;
	}
}