package com.tuitionexpense.main;

import com.tuitionexpense.service.ExpenseWebsiteServices;
import com.tuitionexpense.util.HibernateSessionFactory;

public class EmployeeDriver {

	
	public static void main(String[] args) {
		
		ExpenseWebsiteServices expenseRepo = new ExpenseWebsiteServices();
//	//	Employees employeeLogin = new Employees();
//////		if(expenseRepo.employeeLogin("user2@yahoo.com", "user2") == null ) {
//			System.out.println("login successful");
//		}
//			
//		
//	//	System.out.println("Login successful");
//	//	System.out.println(employeeLogin.getEmployeeId());
//	//	} 
//	//	else {
//	//		System.out.println("Login error");
//	//	}
//		//Employees employee = new Employees(1, "user2@yahoo.com", "user2", "employee" ,"collins", "peter", LocalDate.parse("1977-06-06"));
//		//expenseRepo.createEmployee(employee);
//		
//		
//		String filePath = "c.papoose.path/";
//		Expenses expense = new Expenses(0l, 7, filePath, "pending" , LocalDate.parse("2021-21-02"), "hello2", 1000 );
//		expenseRepo.submitReimbursementRequest(expense);
		
	//	HibernateSessionFactory.getSession();
		
	//	ExpenseWebsiteServices expenseServices = new ExpenseWebsiteServices();
		
	//	Expense newExpense = new Expense(1, 3, "yti2.path.url2", "pending", LocalDate.now(), "eXpenses2 from hibernate", "", 700, "");
//		expenseServices.submitReimbursementRequest(newExpense);
		
	//	Employees newEmployee = new Employees(8, "hiber@gmail.com", "passwrod", "manager", "Joe", "doe", LocalDate.now(), "single", "finance");
	//	expenseServices.createEmployee(newEmployee);
		
	//	System.out.println(expenseRepo.viewPersonalInformation(1));
		
		// printing a list of all employees to the console
	//	System.out.println(expenseRepo.viewAllEmployee());
	}
}
