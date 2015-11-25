package com.qlikauditionproject.servlet.response;

/**
 * Web service response class for DeleteMessageServiceResponse
 *
 * @author Richa Pandey
 *
 */
public class DeleteMessageServiceResponse {

	String status;
	String error;
	int messagesdeleted;

	public DeleteMessageServiceResponse() {
		super();
	}

	public DeleteMessageServiceResponse(String status, String error, int messagesdeleted) {
		super();
		this.status = status;
		this.error = error;
		this.messagesdeleted = messagesdeleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getMessagesdeleted() {
		return messagesdeleted;
	}

	public void setMessagesdeleted(int messagesdeleted) {
		this.messagesdeleted = messagesdeleted;
	}

}
