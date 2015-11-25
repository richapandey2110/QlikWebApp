package com.qlikauditionproject.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlikauditionproject.db.dto.QlikMessageDto;
import com.qlikauditionproject.servlet.response.GetMessageServiceResponse;

/**
 * Web Service class for GetMessageService
 * Mapping: /getmessage
 *
 * @author Richa Pandey
 *
 */
@SuppressWarnings("serial")
public class GetMessageService extends QlikServlet {

	GetMessageServiceResponse webserviceResponse = null;

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
		webserviceResponse = new GetMessageServiceResponse();

		// Fetch web service request parameter from request object
		String queryBy = request.getParameter("queryby");

		try {
			if (queryBy != null && queryBy.length() != 0) {
				List<QlikMessageDto> messagesInDatabase = null;
				if (queryBy.equals("id") && request.getParameter("id") != null) {
					int id = Integer.parseInt(request.getParameter("id"));

					messagesInDatabase = qlikMessageDao.getMessageFromDatabaseById(id);

					if (messagesInDatabase != null) {
						webserviceResponse.setStatus(SUCCESS_STATUS);
						webserviceResponse.setTotalmessages(messagesInDatabase.size());
						webserviceResponse.setQlikmessages(messagesInDatabase);
					} else {
						webserviceResponse.setStatus(ERROR_STATUS);
						webserviceResponse.setError("No message found in database.");
					}
				} else if (queryBy.equals("message") && request.getParameter("message") != null) {
					String message = request.getParameter("message");

					messagesInDatabase = qlikMessageDao.getMessageFromDatabaseByMessageText(message);

					if (messagesInDatabase != null) {
						webserviceResponse.setStatus(SUCCESS_STATUS);
						webserviceResponse.setTotalmessages(messagesInDatabase.size());
						webserviceResponse.setQlikmessages(messagesInDatabase);
					} else {
						webserviceResponse.setStatus(ERROR_STATUS);
						webserviceResponse.setError("No message found in database.");
					}
				} else {
					webserviceResponse.setStatus(ERROR_STATUS);
					webserviceResponse
							.setError("Invalid queryby parameter passed. Please either search by id or by message.");
				}
			} else {
				webserviceResponse.setStatus(ERROR_STATUS);
				webserviceResponse.setError("No message web service parameter passed");
			}
		} catch (Exception ex) {
			webserviceResponse.setStatus(ERROR_STATUS);
			webserviceResponse.setError(ex.getMessage());
		}
		generateWebServiceResponse(response, webserviceResponse);
	}

}
