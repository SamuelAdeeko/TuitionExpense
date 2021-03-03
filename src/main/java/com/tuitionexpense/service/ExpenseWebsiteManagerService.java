package com.tuitionexpense.service;


import java.util.ArrayList;
import java.util.List;

import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;
import com.tuitionexpense.model.Manager;
import com.tuitionexpense.repository.ExpenseManagerWebsiteRepository;
import com.tuitionexpense.repository.ExpenseManagerWebsiteRepositoryImpl;
import com.tuitionexpense.repository.ExpenseWebsiteRepositoryImpl;



public class ExpenseWebsiteManagerService {

	private ExpenseManagerWebsiteRepository managerRepo;
	
	ExpenseWebsiteRepositoryImpl employeeDao ;

	public ExpenseWebsiteManagerService(){
		System.out.println("service");
		this.employeeDao = new ExpenseWebsiteRepositoryImpl();
		this.managerRepo = new ExpenseManagerWebsiteRepositoryImpl();
	}
	
	
	public boolean checkManager(int employeeId){
		List<Manager> manager = new ArrayList<>();
		manager = managerRepo.checkForManager(employeeId);
		for(Manager i: manager) {
			if(i.getEmployeeId() == employeeId && i.getDesignation().equals("manager") || i.getDesignation().equals("g.manager")) {
			System.out.println(manager);
				return true;
			}
		}
		return false;
	}
	
	
	public boolean isValidUser(String email, String password, int employeeId) {
		List<Employees> employee = employeeDao.viewAllEmployees();
		
		for(Employees i: employee) {
			if(i.getEmail().equals(email) && i.getPassword().equals(password) && i.getEmployeeId() == employeeId) {
				
				if(checkManager(employeeId) == true) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	
	public List<Employees> allEmployeesManaged(int managerId) {
		List<Employees> allEmployeesManaged = new ArrayList<>();
		allEmployeesManaged =  managerRepo.allEmployeesManaged(managerId);
		return allEmployeesManaged;
	}

	public List<Integer> getManagedEmployeeId(int managerId) {
		List<Integer> c = new ArrayList<>();
		List<Employees> employee = new ArrayList<>();
		employee = allEmployeesManaged(managerId);
		for(Employees i: employee) {
			c.add(i.getEmployeeId());
		}
		
		
		return c;
	}
	
	
	public List<Expense> viewPendingRequest(int managerId){
		
		return this.managerRepo.viewPendingRequest(managerId);
	}
	
	
	public List<Expense> viewRequestFromManagedEmployee(int managerId, int employeeId) {
		// WRITE A METHOD TO CHECK IF MANAGER ID IS VALID OR IF MANAGER HAS A SESSION
		List <Expense> pendingExpense = new ArrayList<>();
		
		//Get an ArrayList of all the expense request
		pendingExpense = this.managerRepo.viewAllExpenseRequest(managerId);
		List<Expense> empExpense = new ArrayList<>();
		
		// For each to get matching employee Id with Id's in the Expense table
		for(Expense i : pendingExpense) {
			if(i.getEmployeeId() == employeeId) {
				empExpense.add(i);
			}
		}
		
		return empExpense;
	}
	
	// view all approved request and the manager that authorized it.
	
	public List<Expense> viewResolvedEmployeesRequestAuthorization(int managerId) {
		// WRITE A METHOD TO CHECK IF MANAGER ID IS VALID OR IF MANAGER HAS A SESSION
		List <Expense> resolvedExpense = new ArrayList<>();
		
		//Get an ArrayList of all the expense request
		resolvedExpense = this.managerRepo.viewAllExpenseRequest(managerId);
		List<Expense> empExpense = new ArrayList<>();
		
		// For each to get status = approved in the Expense table
		for(Expense i : resolvedExpense) {
			if(i.getStatus().equals("approved")) {
				empExpense.add(i);
			}
		}
		
		return empExpense;
	}
	
	public void approveDenyRequest(int expenseId, String decision, int employeeId){
		String name1 = getManagerLastName(employeeId);
		// Configure to make sure it is an employee they manage
		this.managerRepo.approveDenyRequest(expenseId, decision, name1);
	}
	
	
	public String getManagerLastName(int employeeId2) {
		Employees name = new ExpenseWebsiteRepositoryImpl().viewPersonalInformation(employeeId2);
		String lastName = name.getLastName();
		return lastName;
	}
	
//	public List<Expense> viewEmployeePendingRequest(int managerId){
//		List <Expense> pendingExpense = new ArrayList<>();
//		List<Employees> managedEmployees = allEmployeesManaged(managerId);
//	//	System.out.println(managedEmployees);
//		 pendingExpense = this.managerRepo.viewAllExpenseRequest(managerId);
//		 Expense pending = new Expense();
//		 for(Expense i : pendingExpense) {
//			 for(Employees j: managedEmployees) {
//				 if(i.getEmployeeId() == j.getEmployeeId() && i.getStatus().equals("pending")) {
//					int c = i.getEmployeeId();
//					int f = j.getEmployeeId();
//					System.out.println("i:" + c);
//					System.out.println("j:" + f);
//					 pending.setAmount(i.getAmount());
//					pending.setAuthorizedBy(i.getAuthorizedBy());
//					pending.setComments(i.getComments());
//					pending.setDetails(i.getExpenseImage());
//					pending.setEmployeeId(i.getEmployeeId());
//					pending.setExpenseId(i.getExpenseId());
//					pending.setExpenseImage(i.getExpenseImage());
//					pending.setStatus(i.getStatus());
//					pending.setSubmissionDate(i.getSubmissionDate());
//					pendingExpense.add(pending);
//					return pendingExpense;
//				 }
//			 }
//			
//			 
//		 }
//		 
//		 return null;
//	}
	
	
}
