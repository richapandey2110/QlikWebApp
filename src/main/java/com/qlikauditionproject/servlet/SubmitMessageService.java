package com.qlikauditionproject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlikauditionproject.db.dto.QlikMessageDto;
import com.qlikauditionproject.servlet.response.SubmitMessageServiceResponse;
/**
 * Web service class for SubmitMessageService
 * Mapping: /submitmessage
 *
 * @author Richa Pandey
 *
 */
@SuppressWarnings("serial")
public class SubmitMessageService extends QlikServlet {
	
	SubmitMessageServiceResponse webserviceResponse = null;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		webserviceResponse = new SubmitMessageServiceResponse();
		
		// Fetch web service request parameter from request object
		String receivedWebServiceMessage = request.getParameter("message");
		
		try {
			if(receivedWebServiceMessage != null && receivedWebServiceMessage.length() != 0) {
				QlikMessageDto insertedMessageInDatabase = qlikMessageDao.insertMessageInDatabase(receivedWebServiceMessage);
				webserviceResponse.setStatus(SUCCESS_STATUS);
				webserviceResponse.setSubmittedQlikMessage(insertedMessageInDatabase);
			} else {
				webserviceResponse.setStatus(ERROR_STATUS);
				webserviceResponse.setError("No message web service parameter passed");
			}
		} catch(Exception ex) {
			webserviceResponse.setStatus(ERROR_STATUS);
			webserviceResponse.setError(ex.getMessage());
		}
		generateWebServiceResponse(response, webserviceResponse);
	}

}
