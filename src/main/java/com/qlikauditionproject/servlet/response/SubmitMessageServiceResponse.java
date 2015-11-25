package com.qlikauditionproject.servlet.response;

import com.qlikauditionproject.db.dto.QlikMessageDto;

/**
 * Web service response class for SubmitMessageServiceResponse
 *
 * @author Richa Pandey
 *
 */
public class SubmitMessageServiceResponse {

	private String status;
	private String error;
	private QlikMessageDto submittedQlikMessage;
	
	public SubmitMessageServiceResponse() {
	}

	public SubmitMessageServiceResponse(String status, String error, QlikMessageDto submittedQlikMessage) {
		super();
		this.status = status;
		this.error = error;
		this.submittedQlikMessage = submittedQlikMessage;
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

	public QlikMessageDto getSubmittedQlikMessage() {
		return submittedQlikMessage;
	}

	public void setSubmittedQlikMessage(QlikMessageDto submittedQlikMessage) {
		this.submittedQlikMessage = submittedQlikMessage;
	}

}
