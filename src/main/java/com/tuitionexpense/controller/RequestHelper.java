package com.tuitionexpense.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
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
			response.setStatus(200);
			break;
		
		case "/dispatcher/employee/info":
			
			final int EMPLOYEEID2 = Integer.parseInt(request.getParameter("employeeId"));
			
			ObjectMapper obj = new ObjectMapper();
			String json = obj.writeValueAsString(this.expenseServices.viewPersonalInformation(EMPLOYEEID2));
			System.out.println(json);
			response.getWriter().write(json);
			response.setStatus(200);
		 	break;
		 	
		 case "/dispatcher/employees/all":
			 
			 // Code to view all employees work
			 ObjectMapper obj1 = new ObjectMapper();
			 final String json1 = obj1.writeValueAsString(this.expenseServices.viewAllEmployee());
			 System.out.println(json1);
			 response.getWriter().write(json1);
			 response.setStatus(200);
			 break;
		 
		 case "/dispatcher/Expense/pending/view":
			 final int EMPLOYEEID1 = Integer.parseInt(request.getParameter("employeeId"));
			 System.out.println(EMPLOYEEID1);
			 ObjectMapper obj2 = new ObjectMapper();
			 final String JSON2 = obj2.writeValueAsString(this.expenseServices.viewEmployeePendingExpense(EMPLOYEEID1));
			 System.out.println(JSON2);
			 response.getWriter().write(JSON2);
			 response.setStatus(200);
			 break;
			 
		 case "/dispatcher/Expense/solved/view":
			 final int EMPLOYEEID = Integer.parseInt(request.getParameter("employeeId"));
			 ObjectMapper obj3 = new ObjectMapper();
			 final String JSON3 = obj3.writeValueAsString(this.expenseServices.viewResolvedReimbursement(EMPLOYEEID));
			 response.getWriter().write(JSON3);
			 response.setStatus(200);
			 break;
			 
		 case "/dispatcher/manager/pendingrequest/view":
			 // CONFIGURE BACKENED TO GET MANAAGER ID AFTER SUCCESSFUL LOGIN
			 final int MANAGERID = 3;
			 ObjectMapper obj4 = new ObjectMapper();
			 final String JSON4 = obj4.writeValueAsString(this.managerServices.viewPendingRequest(MANAGERID));
			 System.out.println(JSON4);
			 response.getWriter().write(JSON4);
			 response.setStatus(200);
			 break;
			 
			 
		 case "/dispatcher/manager/resolvedrequest/authorized/view":
			 // CONFIGURE BACKENED TO GET MANAAGER ID AFTER SUCCESSFUL LOGIN
			 final int MANAGERID1 = 3;
			 ObjectMapper obj5 = new ObjectMapper();
			 final String JSON5 = obj5.writeValueAsString(this.managerServices.viewResolvedEmployeesRequestAuthorization(MANAGERID1));
			 System.out.println(JSON5);
			 response.getWriter().write(JSON5);
			 response.setStatus(200);
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
					final int EMPLOYEEID = Integer.parseInt(request.getParameter("employeeId"));
					System.out.println(USEREMAIL);
					System.out.println(USERPASSWORD);
					if(this.expenseServices.isValidUser(USEREMAIL, USERPASSWORD, EMPLOYEEID)) {
						// let us grant the client a session (we won't be truly restful because we are storing some client data on the server
						HttpSession session = request.getSession();
						session.setAttribute("employeeId", EMPLOYEEID);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/home.html");
						dispatcher.forward(request, response);
						
						
						
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
					final int MANAGERID = Integer.parseInt(request.getParameter("employeeId"));
					if(this.managerServices.isValidUser(MANAGERUSEREMAIL, MANAGERUSERPASSWORD, MANAGERID)) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/homeManager.html");
						dispatcher.forward(request, response);
						response.setStatus(200);
//						HttpSession session = request.getSession();
//						session.setAttribute("email",USEREMAIL);
						
					} else {
						response.setStatus(404);
						response.getWriter().write("Invalid username and password");
						}
					
					break;
			
			case "/dispatcher/expense/new":
				final String IMAGE = request.getParameter("image");
				final long AMOUNT = Long.parseLong(request.getParameter("amount"));
				final String DETAILS = request.getParameter("details");
				final String STATUS = request.getParameter("status");
				final int EMPLOYEEID1 = Integer.parseInt(request.getParameter("employeeId"));
				final int EXPENSEID = Integer.parseInt(request.getParameter("expenseId"));
				Expense newExpense = new Expense(EXPENSEID, EMPLOYEEID1, IMAGE, STATUS, DETAILS, AMOUNT);
				this.expenseServices.submitReimbursementRequest(newExpense);
				response.getWriter().write("Submitted Successfully");
				response.setStatus(200);
				break;
				
			case "/dispatcher/manager/singlerequest/view":
				 // CONFIGURE BACKENED TO GET MANAAGER ID AFTER SUCCESSFUL LOGIN
				 final int MANAGERID1 = 3;
				 final int EMPLOYEEID2 = Integer.parseInt(request.getParameter("employeeId1"));
				 ObjectMapper obj5 = new ObjectMapper();
				 final String JSON5 = obj5.writeValueAsString(this.managerServices.viewRequestFromManagedEmployee(MANAGERID1, EMPLOYEEID2));
				 System.out.println(JSON5);
				 response.getWriter().write(JSON5);
				 response.setStatus(200);
				 break;
				
			case "/dispatcher/employee/maritalstatus":
				final String MARITALSTATUS = request.getParameter("marital_status");
				final int EMPLOYEEID3 = Integer.parseInt(request.getParameter("employeeId3"));
				this.expenseServices.updateMaritalStatus(MARITALSTATUS, EMPLOYEEID3);
				response.getWriter().write("Marital Status successfully changed to " + MARITALSTATUS);
				response.setStatus(200);
				break;
				
			case "/dispatcher/manager/request/decision":
				 // CONFIGURE BACKENED TO GET MANAAGER ID AFTER SUCCESSFUL LOGIN
				final int EMPLOYEEID4 = Integer.parseInt(request.getParameter("employeeId4"));
				final int EXPENSEID1 = Integer.parseInt("expenseid");
				final String DECISION = request.getParameter("decision");
				this.managerServices.approveDenyRequest(EXPENSEID1, DECISION, EMPLOYEEID4);
				response.getWriter().write("Marital Status successfully changed to " + DECISION);
				response.setStatus(200);
				break;
				
			
			default:
				response.setStatus(404);
				response.getWriter().write("Invalid link");
	
			//expense_id, employee_id, amount, authorized_by, comments,
			//	details, expense_date, expense_image, status
		}
		
		
		
		
	}
}
