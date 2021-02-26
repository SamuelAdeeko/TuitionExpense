package com.tuitionexpense.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloWorldServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * 
		 * 
		 * 
		 */
		
//		List<Expenses> expense1 = new ArrayList<>();
//		expense1.add(new Expense("married", "expense cost", 20000));
		
//		ObjectMapper obj = new ObjectMapper();
//		String json = obj.writeValueAsString(expense1);
//		System.out.println(json);
//		response.getWriter().write(json);
//		final String RESOURCE = request.getRequestURI().replace("/TuitionExpense", "");
//		response.getWriter().write("Hello, World");
////		final String URI = request.getRequestURI();
////		System.out.println(URI);
////		
////		final String RESOURCE = URI.replace("TuitionExpense/", "");
////		System.out.println(URI);
//		
//		switch(RESOURCE) {
//		 case "/hello":
//			 System.out.println("Hello and welcome to the hello page");
//			 break;
//		 
//		 case "/home":
//			 System.out.println("Welcome to home page");
//			 break;
//			 
//		 default:
//				 System.out.println("Invalid input, try again later");
//		}
		
//		 response.setContentType("application/json");
//		 response.getWriter().write("<h1> This is my header</h1>");
		
		
//		response.setContentType("application/json");
//		response.getWriter().write("<h1> This is header 1 </h1>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
