package com.qlikauditionproject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlikauditionproject.servlet.response.DeleteMessageServiceResponse;

/**
 * Web Service class for DeleteMessageService
 * Mapping: /deletemessage
 *
 * @author Richa Pandey
 *
 */
@SuppressWarnings("serial")
public class DeleteMessageService extends QlikServlet {

	DeleteMessageServiceResponse webserviceResponse = null;

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
		webserviceResponse = new DeleteMessageServiceResponse();

		// Fetch web service request parameter from request object
		String queryBy = request.getParameter("queryby");

		try {
			if (queryBy != null && queryBy.length() != 0) {
				int recordsDeleted = 0;

				if (queryBy.equals("id") && request.getParameter("id") != null) {
					int id = Integer.parseInt(request.getParameter("id"));

					recordsDeleted = qlikMessageDao.deleteMessageFromDatabaseById(id);

					if (recordsDeleted > 0) {
						webserviceResponse.setStatus(SUCCESS_STATUS);
						webserviceResponse.setMessagesdeleted(recordsDeleted);
					} else {
						webserviceResponse.setStatus(ERROR_STATUS);
						webserviceResponse.setError("No message found in database.");
					}
				} else if (queryBy.equals("message") && request.getParameter("message") != null) {
					String message = request.getParameter("message");

					recordsDeleted = qlikMessageDao.deleteMessageFromDatabaseByMessageText(message);

					if (recordsDeleted > 0) {
						webserviceResponse.setStatus(SUCCESS_STATUS);
						webserviceResponse.setMessagesdeleted(recordsDeleted);
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
