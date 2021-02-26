package com.tuitionexpense.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*
 *  This Servlet is the Front Controller. It's a type of design pattern. 
 *  All the HttpRequest and response will move through this Servlet.
 */

public class DispatcherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
//	private static Logger LOG = LogManager.getLogger(DispatcherServlet.class);
	
	private RequestHelper requestHelper;
	
	public DispatcherServlet() {
		requestHelper = new RequestHelper();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		this.requestHelper.processGet(request, response);
	//	response.getWriter().write("This is the dispatch layer");;
	
//		
//		Writer writer = response.getWriter();
//		//response.setContentType("text/html");
//				response.setContentType("application/json");
//				writer.write("<h1> This is a header </h1>");		
		
		
	
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.requestHelper.processPost(request, response);
	}





}
 