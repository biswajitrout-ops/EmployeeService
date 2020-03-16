package com.biswajit.usecase.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biswajit.usecase.employee.model.EmployeeTO;

/**
 * 
 * @author biswajit.rout
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeTO, Long> {

	@Query("SELECT emp from EmployeeTO as emp WHERE emp.emailId = :emailId")
	EmployeeTO findByEmailID(String emailId);
	
	@Query("SELECT emp from EmployeeTO as emp WHERE emp.id = :id")
	EmployeeTO findByEmployeeID(String id);

	@Query("SELECT emp from EmployeeTO as emp WHERE emp.companyName = :companyName")
	List<EmployeeTO> findByCompanyName(String companyName);
}