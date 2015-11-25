package com.qlikauditionproject.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qlikauditionproject.db.dao.QlikMessageDao;

/**
 * Web Service base class for QlikService.
 * Generic message class which contains common service implementation methods.
 *
 * @author Richa Pandey
 *
 */
@SuppressWarnings("serial")
public class QlikServlet extends HttpServlet {
	
	public static String SUCCESS_STATUS = "SUCCESS";
	public static String ERROR_STATUS = "ERROR";
	
	QlikMessageDao qlikMessageDao = new QlikMessageDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("QlikServlet Executed - GET Service");
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("QlikServlet Executed - POST Service");
		out.flush();
		out.close();
	}
	
	/**
	 * Method to generate JSON response for web service requests.
	 * 
	 * @param response
	 * @param webserviceResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void generateWebServiceResponse(HttpServletResponse response, Object webserviceResponse) throws ServletException, IOException {
		// Code to generate GET Web service response
		PrintWriter out = response.getWriter();

		Gson gson = new GsonBuilder().create();
		gson.toJson(webserviceResponse, out);

		out.flush();
		out.close();
	}

}
