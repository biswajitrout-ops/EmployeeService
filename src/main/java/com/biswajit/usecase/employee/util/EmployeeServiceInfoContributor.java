package com.biswajit.usecase.employee.util;

import java.util.Collections;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * 
 * @author biswajit.rout
 *
 */
@Component
public class EmployeeServiceInfoContributor implements InfoContributor {

	@Override
	public void contribute(Info.Builder builder) {
		builder.withDetail("details", Collections.singletonMap("description",
				"This is the Employee Registration service, which is discovery server aware, and this service will be Called by school Microservice, fro student details, which is again dicovery server aware!!! "));
	}
}