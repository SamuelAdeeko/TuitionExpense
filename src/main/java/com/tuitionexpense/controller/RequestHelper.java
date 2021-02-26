package com.tuitionexpense.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;
import com.tuitionexpense.service.ExpenseWebsiteServices;

public class RequestHelper {
	
	private ExpenseWebsiteServices expenseServices;

//	public static ExpenseWebsiteServices expenseRepo = new ExpenseWebsiteServices();
	
	public RequestHelper() {
		expenseServices = new ExpenseWebsiteServices();
	}
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
//		final String URI = request.getRequestURI();
//		System.out.println(URI);
	//	LOG.debug(URI);
		
		
		final String RESOURCE = request.getRequestURI().replace("/TuitionExpense", "");
	//	response.getWriter().write("Hello, World");
		System.out.println(RESOURCE);
		
		switch(RESOURCE) {
		// login test works
		case "/dispatcher/login":
			final String USEREMAIL = request.getParameter("email");
			final String USERPASSWORD = request.getParameter("password");
			
			if(this.expenseServices.isValidUser(USEREMAIL, USERPASSWORD)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/home.html");
				dispatcher.forward(request, response);
				
				HttpSession session = request.getSession();
				session.setAttribute("email",USEREMAIL);
				
			} else {
				response.setStatus(404);
				response.getWriter().write("Invalid username and password");
				}
			
			break;
			
		case "/dispatcher/logout":
			HttpSession session = request.getSession(false);
			if(session != null) {
			session.invalidate();
			response.getWriter().write("Your session has been terminated");
			}
			
			break;
		 case "/dispatcher/employee/info":
			 
//			 Employees employeeInfo = new Employees();
//			 final String ID = request.getParameter("id");
//			 this.expenseServices.viewPersonalInformation(ID)
//			 
//			ObjectMapper obj = new ObjectMapper();
//			String json = obj.writeValueAsString();
//			System.out.println(json);
//			response.getWriter().write(json);
		 	break;
		 	
		 case "/dispatcher/employees/all":
			 
			 // Code to view all employees work
			 ObjectMapper obj = new ObjectMapper();
			 final String json = obj.writeValueAsString(this.expenseServices.viewAllEmployee());
			 System.out.println(json);
			 response.getWriter().write(json);
			 response.getWriter().write("Hello and welcome to the dispatcher tool");
			 break;
		 
		 case "/dispatcher/Expense/pending/view":
			 final String userId = request.getParameter("userid");
	//		 expenseRepo.viewPendingReimbursement(userId);
			 response.getWriter().write("Welcome to home page");
			 break;
		 
		 case "/dispatcher/contact":
			 response.getWriter().write("Welcome to contact page");
			 break;
			 
		 
		 default:
			 response.getWriter().write("Invalid input, try again tomorrow");
		}
		
		
		
		
		
//		final String RESOURCE = URI.replace("/ExpenseWebsite/dispatcher", "");
//		System.out.println(RESOURCE);
//		
//		if(RESOURCE.equals("/hello")) {
//			response.getWriter().write("Hi back! I'm a Java App!");
//		} else if(RESOURCE.equals("/goodbye")) {
//			response.getWriter().write("Sad to see you go, Goodbye!");
//		} else {
//			response.getWriter().write("That is not a supported operation, pls check your input and try again.");
//		}
	
	

	}
	
	

	public void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//	final String URI = request.getRequestURI();
		final String RESOURCE = request.getRequestURI().replace("/TuitionExpense", "");
		System.out.println(RESOURCE);
		switch(RESOURCE) {
//		case "/dispatcher/login":
//			final String USERNAME = request.getParameter("username");
//			final String PASSWORD = request.getParameter("userPassword");
//			this.expenseServices.employeeLogin(USERNAME, PASSWORD);
		
	//		break;
			
			case "/dispatcher/expense/new":
				final String EXPENSEIMAGE = request.getParameter("image");
				final long AMOUNT = Long.parseLong(request.getParameter("amount"));
				final String DETAILS = request.getParameter("details");
				final String STATUS = request.getParameter("status");
				final long EMPLOYEEID = Long.parseLong(request.getParameter("employeeId"));
			//	final LocalDate SUBMISSIONDATE = LocalDate.parse("2021-2-2");
				final String COMMENTS = request.getParameter("comments");
				final String AUTHORIZEDBY = request.getParameter("authorizedBy");
						
				Expense newExpense = new Expense(1,EMPLOYEEID, EXPENSEIMAGE, STATUS, LocalDate.now(),DETAILS, COMMENTS, AMOUNT, AUTHORIZEDBY);
				this.expenseServices.submitReimbursementRequest(newExpense);
	//			response.getWriter().write("Submitted Successfully");
				break;
			default:
				response.setStatus(404);
				response.getWriter().write("Invalid link");
	
			//expense_id, employee_id, amount, authorized_by, comments,
			//	details, expense_date, expense_image, status
		}
		
		
	}
}
