package com.qlikauditionproject.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlikauditionproject.db.dto.QlikMessageDto;
import com.qlikauditionproject.servlet.response.ListMessagesServiceResponse;

/**
 * Web Service class for ListMessageService
 * Mapping: /listmessages
 *
 * @author Richa Pandey
 *
 */
@SuppressWarnings("serial")
public class ListMessagesService extends QlikServlet {

	ListMessagesServiceResponse webserviceResponse = null;

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
		webserviceResponse = new ListMessagesServiceResponse();

		try {
			List<QlikMessageDto> messagesInDatabase = qlikMessageDao.getMessagesFromDatabase();
			webserviceResponse.setStatus(SUCCESS_STATUS);
			webserviceResponse.setTotalmessages(messagesInDatabase.size());
			webserviceResponse.setQlikmessages(messagesInDatabase);
		} catch (Exception ex) {
			webserviceResponse.setStatus(ERROR_STATUS);
			webserviceResponse.setError(ex.getMessage());
		}
		generateWebServiceResponse(response, webserviceResponse);
	}

}
