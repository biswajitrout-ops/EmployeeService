package com.biswajit.usecase.employee;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.biswajit.usecase.employee.model.EmployeeTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

	@LocalServerPort
	int randomServerPort;

	@Test
	public void createEmployeeSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/employeeservice/api/v1/employee";
		URI uri = new URI(baseUrl);

		EmployeeTO employee = new EmployeeTO();

		employee.setFirstName("Biswajit");
		employee.setLastName("Rout");
		employee.setEmailId("biswajit.rout@accenture.com");
		employee.setCompanyName("Accenture");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<EmployeeTO> request = new HttpEntity<>(employee, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		System.out.println("Biswajit11111....." + result);

		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void createEmployeeFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/employeeservice/api/v1/employee";
		URI uri = new URI(baseUrl);

		EmployeeTO employee = new EmployeeTO();

		employee.setFirstName("Biswajeet");
		employee.setLastName("Rout");
		employee.setEmailId("biswajit.rout@accenture.com");
		employee.setCompanyName("Accenture");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<EmployeeTO> request = new HttpEntity<>(employee, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		System.out.println("Biswajit22222....." + result);

		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void getEmployeeByCompanyNameSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/employeeservice/api/v1/company/Accenture";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		System.out.println("Biswajit33333....." + result);

		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void getEmployeeByCompanyNameFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/employeeservice/api/v1/company/HP";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		System.out.println("Biswajit44444....." + result);

		Assertions.assertEquals(410, result.getStatusCodeValue());
	}

}