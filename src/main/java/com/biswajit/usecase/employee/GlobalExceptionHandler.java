package com.biswajit.usecase.employee;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.biswajit.usecase.employee.util.EmployeeServiceException;
import com.biswajit.usecase.employee.util.Message;

/**
 * 
 * The GlobalExceptionHandler class.
 * 
 * @author biswajit.rout
 * 
 */
@ControllerAdvice
@RestController
@Configuration
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This method handles all the exceptions.
	 *
	 * @param exception the APIWebPortal exception object
	 * @return the message back to the user
	 * 
	 */
	@ExceptionHandler(EmployeeServiceException.class)
	public final List<Message> handleAllExceptions(EmployeeServiceException exception) {
		String stackTrace = ExceptionUtils.getStackTrace(exception);
		if (stackTrace.contains("IOException")) {
			return constructErrorMessages("500",
					"File Error. Please try after sometime. If problem persists please reach out to Support.");
		} else {
			return constructErrorMessages("999",
					"Your request cannot be processed at this time. Please reach out to Support.");
		}
	}

	/**
	 * This method handles all the exceptions.
	 *
	 * @param exception the Exception object
	 * @return the message back to the user
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public final List<Message> handleAllExceptions(Exception exception) {
		return constructErrorMessages("999",
				"Your request cannot be processed at this time. Please reach out to Support.");
	}

	/**
	 * This method is used to generate error messages
	 *
	 * @param code            the response code object
	 * @param strMessage      the response message object
	 * @param strResponseType the response type object
	 * @return the messages back to the user
	 * 
	 */
	private List<Message> constructErrorMessages(String code, String strMessage) {
		Message message = new Message();
		message.setResponseCode(code);
		message.setResponseMessage(strMessage);
		List<Message> responseList = new ArrayList<>(1);
		responseList.add(message);
		return responseList;
	}
}
