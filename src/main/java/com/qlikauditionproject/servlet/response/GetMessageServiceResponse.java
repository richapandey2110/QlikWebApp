package com.qlikauditionproject.servlet.response;

import java.util.List;

import com.qlikauditionproject.db.dto.QlikMessageDto;

/**
 * Web service response class for GetMessageServiceResponse
 *
 * @author Richa Pandey
 *
 */
public class GetMessageServiceResponse {

	String status;
	String error;
	int totalmessages = 0;
	List<QlikMessageDto> qlikmessages;

	public GetMessageServiceResponse() {
		super();
	}

	public GetMessageServiceResponse(String status, String error, int totalmessages,
			List<QlikMessageDto> qlikmessages) {
		super();
		this.status = status;
		this.error = error;
		this.totalmessages = totalmessages;
		this.qlikmessages = qlikmessages;
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

	public int getTotalmessages() {
		return totalmessages;
	}

	public void setTotalmessages(int totalmessages) {
		this.totalmessages = totalmessages;
	}

	public List<QlikMessageDto> getQlikmessages() {
		return qlikmessages;
	}

	public void setQlikmessages(List<QlikMessageDto> qlikmessages) {
		this.qlikmessages = qlikmessages;
	}

}
