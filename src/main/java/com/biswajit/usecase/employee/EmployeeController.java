package com.biswajit.usecase.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.biswajit.usecase.employee.model.EmployeeTO;
import com.biswajit.usecase.employee.model.EmployeeVO;
import com.biswajit.usecase.employee.util.EmployeeServiceException;

/**
 * 
 * @author biswajit.rout
 *
 */
@RestController
@RequestMapping("/employeeservice/api/v1")
public class EmployeeController {

	/** The employee service. */
	private @Autowired @Lazy(value = true) EmployeeService employeeService;

	private @Autowired @Lazy(value = true) EmployeeRepository employeeRepository;

	@CrossOrigin
	@PostMapping("/employee")
	public EmployeeVO createEmployee(@RequestBody @Valid EmployeeTO employee) throws EmployeeServiceException {
		if (employee.getId() == null) {
			UUID uniqueKey = UUID.randomUUID();
			employee.setId(uniqueKey.toString());
		}
		return employeeService.createEmployee(employee);
	}

	@CrossOrigin
	@GetMapping("/company/{companyName}")
	public List<EmployeeVO> getEmployeeByCompanyName(@PathVariable(value = "companyName") String companyName)
			throws EmployeeServiceException {
		return employeeService.getEmployeeByCompanyName(companyName);
	}

	@CrossOrigin
	@GetMapping("/id/{id}")
	public EmployeeVO getEmployeeById(@PathVariable(value = "id") String employeeId) throws EmployeeServiceException {
		return employeeService.getEmployeeByID(employeeId);
	}

	@CrossOrigin
	@GetMapping("/employees")
	public List<EmployeeTO> getAllEmployees() throws EmployeeServiceException {
		return employeeRepository.findAll();
	}

	@CrossOrigin
	@PutMapping("/updateEmployee/{id}")
	public EmployeeVO updateEmployee(@PathVariable(value = "id") String employeeId,
			@Valid @RequestBody EmployeeTO employeeDetails) throws EmployeeServiceException {
		return employeeService.updateEmployee(employeeId, employeeDetails);
	}

	@CrossOrigin
	@DeleteMapping("/deleteEmployee/{id}")
	public EmployeeVO deleteEmployee(@PathVariable(value = "id") String employeeId) throws EmployeeServiceException {
		return employeeService.deleteEmployee(employeeId);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return errors;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getConstraintViolations().forEach(cv -> {
			errors.put("message", cv.getMessage());
			errors.put("path", (cv.getPropertyPath()).toString());
		});
		return errors;
	}
}