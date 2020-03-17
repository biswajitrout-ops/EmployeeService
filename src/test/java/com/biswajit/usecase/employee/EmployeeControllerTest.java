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
		final String apiURL = "http://localhost:" + randomServerPort + "/employeeservice/api/v1/employee";
		URI apiURI = new URI(apiURL);

		EmployeeTO employee = new EmployeeTO();
		employee.setFirstName("Biswajit");
		employee.setLastName("Rout");
		employee.setEmailId("biswajit.rout@accenture.com");
		employee.setCompanyName("Accenture");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		/* String tokenURL = "http://localhost:" + randomServerPort + "/token";
		URI tokenURI = new URI(tokenURL);
		AuthRequest user = new AuthRequest();
		user.setUsername("admin");
		user.setPassword("password");
		HttpEntity<AuthRequest> tokenRequest = new HttpEntity<>(user, headers);
		ResponseEntity<String> tokenResult = restTemplate.postForEntity(tokenURI, tokenRequest, String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = null;
		try {
			map = mapper.readValue(tokenResult.getBody().toString(), new TypeReference<Map<String, String>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		String token = map.get("token");
		System.out.println("TOKENNNNNNN....." + token);
		headers.set("Authorization", "Bearer" + token); */

		HttpEntity<EmployeeTO> request = new HttpEntity<>(employee, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(apiURI, request, String.class);

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

		/* String tokenURL = "http://localhost:" + randomServerPort + "/token";
		URI tokenURI = new URI(tokenURL);
		AuthRequest user = new AuthRequest();
		user.setUsername("admin");
		user.setPassword("password");
		HttpEntity<AuthRequest> tokenRequest = new HttpEntity<>(user, headers);
		ResponseEntity<String> tokenResult = restTemplate.postForEntity(tokenURI, tokenRequest, String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = null;
		try {
			map = mapper.readValue(tokenResult.getBody().toString(), new TypeReference<Map<String, String>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		String token = map.get("token");
		System.out.println("TOKENNNNNNN....." + token);
		headers.set("Authorization", "Bearer" + token); */

		HttpEntity<EmployeeTO> request = new HttpEntity<>(employee, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		System.out.println("Biswajit22222....." + result);

		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void getEmployeeByCompanyNameSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/employeeservice/api/v1/employees";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		System.out.println("Biswajit33333....." + result);

		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

}