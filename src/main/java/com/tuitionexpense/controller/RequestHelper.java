package com.tuitionexpense.controller;

import java.io.IOException;
import java.sql.Date;
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
import com.tuitionexpense.service.ExpenseWebsiteManagerService;
import com.tuitionexpense.service.ExpenseWebsiteServices;

public class RequestHelper {
	
	private ExpenseWebsiteServices expenseServices;
	private ExpenseWebsiteManagerService managerServices;

//	public static ExpenseWebsiteServices expenseRepo = new ExpenseWebsiteServices();
	
	public RequestHelper() {
		expenseServices = new ExpenseWebsiteServices();
		managerServices = new ExpenseWebsiteManagerService();
	}
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
//		final String URI = request.getRequestURI();
//		System.out.println(URI);
	//	LOG.debug(URI);
		
		
		final String RESOURCE = request.getRequestURI().replace("/TuitionExpense", "");
	//	response.getWriter().write("Hello, World");
		System.out.println(RESOURCE);
		
		switch(RESOURCE) {
		
		case "/dispatcher":
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("/index.html");
			dispatcher1.forward(request, response);
			break;
			
		
			
		
			
		case "/dispatcher/logout":
			HttpSession session = request.getSession(false);
			if(session != null) {
			session.invalidate();
			}
			response.getWriter().write("Your session has been terminated");
			break;
		
		case "/dispatcher/employee/info":
		
		//	 Employees employeeInfo = new Employees();
		//	 final int ID = Integer.parseInt(request.getParameter("id"));
		//	 employeeInfo = this.expenseServices.viewPersonalInformation(ID);
			 
			ObjectMapper obj = new ObjectMapper();
			String json = obj.writeValueAsString(this.expenseServices.viewPersonalInformation());
			System.out.println(json);
			response.getWriter().write(json);
		 	break;
		 	
		 case "/dispatcher/employees/all":
			 
			 // Code to view all employees work
			 ObjectMapper obj1 = new ObjectMapper();
			 final String json1 = obj1.writeValueAsString(this.expenseServices.viewAllEmployee());
			 System.out.println(json1);
			 response.getWriter().write(json1);
			 response.setStatus(200);
			 response.getWriter().write("Hello and welcome to the dispatcher tool");
			 break;
		 
		 case "/dispatcher/Expense/pending/view":
			 
			 ObjectMapper obj2 = new ObjectMapper();
			 final String JSON2 = obj2.writeValueAsString(this.expenseServices.viewEmployeePendingExpense());
			 System.out.println(JSON2);
			 response.getWriter().write(JSON2);
			 response.getWriter().write("Welcome to home page");
			 break;
			 
		 case "/dispatcher/Expense/solved/view":
			 
			 ObjectMapper obj3 = new ObjectMapper();
			 final String JSON3 = obj3.writeValueAsString(this.expenseServices.viewResolvedReimbursement());
			 response.getWriter().write(JSON3);
			 break;
			 
		 case "/dispatcher/manager/pendingrequest/view":
			 // CONFIGURE BACKENED TO GET MANAAGER ID AFTER SUCCESSFUL LOGIN
			 final int MANAGERID = 3;
			 ObjectMapper obj4 = new ObjectMapper();
			 final String JSON4 = obj4.writeValueAsString(this.managerServices.viewPendingRequest(MANAGERID));
			 System.out.println(JSON4);
			 response.getWriter().write(JSON4);
			 response.getWriter().write("Welcome to home page");
			 break;
			 
		 
			 
		 case "/dispatcher/contact":
			 response.getWriter().write("Welcome to contact page");
			 break;
			 
		 
		 default:
			 response.setStatus(404);
			 response.getWriter().write("'From processGet' Invalid input, please try again");
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
		
		// login test works
				case "/dispatcher/login":
					final String USEREMAIL = request.getParameter("email");
					final String USERPASSWORD = request.getParameter("password");
					
					if(this.expenseServices.isValidUser(USEREMAIL, USERPASSWORD)) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/home.html");
						dispatcher.forward(request, response);
						
						// let us grant the client a session (we won't be truly restful because we are storing some client data on the server
			//			HttpSession session = request.getSession();
			//			session.setAttribute("userEmail", USEREMAIL);
						
						// when the client session ends, all the client stored attributes is deleted
					} else {
						response.setStatus(404);
						response.getWriter().write("Invalid username and password");
						}
					
					break;
//		case "/dispatcher/login":
//			final String USERNAME = request.getParameter("username");
//			final String PASSWORD = request.getParameter("userPassword");
//			this.expenseServices.employeeLogin(USERNAME, PASSWORD);
		
	//		break;
					
				case "/dispatcher/manager/login":
					final String MANAGERUSEREMAIL = request.getParameter("email");
					final String MANAGERUSERPASSWORD = request.getParameter("password");
					
					if(this.managerServices.isValidUser(MANAGERUSEREMAIL, MANAGERUSERPASSWORD)) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/home.html");
						dispatcher.forward(request, response);
						
//						HttpSession session = request.getSession();
//						session.setAttribute("email",USEREMAIL);
						
					} else {
						response.setStatus(404);
						response.getWriter().write("Invalid username and password");
						}
					
					break;
			
			case "/dispatcher/expense/new":
				final String EXPENSEIMAGE = request.getParameter("image");
				final long AMOUNT = Long.parseLong(request.getParameter("amount"));
				final String DETAILS = request.getParameter("details");
				final String STATUS = request.getParameter("status");
				final int EMPLOYEEID = Integer.parseInt(request.getParameter("employeeId"));
			//	final LocalDate SUBMISSIONDATE = LocalDate.parse("2021-2-2");
				final String COMMENTS = request.getParameter("comments");
				final String AUTHORIZEDBY = request.getParameter("authorizedBy");
						
				Expense newExpense = new Expense(20, 2, "path/url", STATUS, Date.valueOf("2021-01-01"), DETAILS, "in review", AMOUNT, AUTHORIZEDBY);
				this.expenseServices.submitReimbursementRequest(newExpense);
				response.getWriter().write("Submitted Successfully");
				break;
				
			case "/dispatcher/manager/singlerequest/view":
				 // CONFIGURE BACKENED TO GET MANAAGER ID AFTER SUCCESSFUL LOGIN
				 final int MANAGERID1 = 3;
				 final int EMPLOYEEID1 = Integer.parseInt(request.getParameter("employeeId1"));
				 ObjectMapper obj5 = new ObjectMapper();
				 final String JSON5 = obj5.writeValueAsString(this.managerServices.viewRequestFromManagedEmployee(MANAGERID1, EMPLOYEEID1));
				 System.out.println(JSON5);
				 response.getWriter().write(JSON5);
				 response.setStatus(200);
				 break;
				
			case "/dispatcher/employee/maritalstatus":
				final String MARITALSTATUS = request.getParameter("marital_status");
				this.expenseServices.updateMaritalStatus(MARITALSTATUS);
				response.getWriter().write("Marital Status successfully changed to " + MARITALSTATUS);
				
				break;
				
			case "/dispatcher/manager/request/decision":
				 // CONFIGURE BACKENED TO GET MANAAGER ID AFTER SUCCESSFUL LOGIN
				final int MANAGERID2 = 3;
				final int EXPENSEID = Integer.parseInt("expenseid");
				final String DECISION = request.getParameter("decision");
				this.managerServices.approveDenyRequest(MANAGERID2, DECISION, EXPENSEID);
				response.getWriter().write("Marital Status successfully changed to " + DECISION);
				
				break;
				
			
			default:
				response.setStatus(404);
				response.getWriter().write("Invalid link");
	
			//expense_id, employee_id, amount, authorized_by, comments,
			//	details, expense_date, expense_image, status
		}
		
		
		
		
	}
}
