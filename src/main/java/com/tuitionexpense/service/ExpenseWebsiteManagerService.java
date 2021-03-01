package com.tuitionexpense.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;
import com.tuitionexpense.model.Manager;
import com.tuitionexpense.repository.ExpenseManagerWebsiteRepository;
import com.tuitionexpense.repository.ExpenseManagerWebsiteRepositoryImpl;
import com.tuitionexpense.repository.ExpenseWebsiteRepositoryImpl;

import javassist.expr.NewArray;

public class ExpenseWebsiteManagerService {

	private ExpenseManagerWebsiteRepository managerRepo;
	private int employeeId;
	
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public ExpenseWebsiteManagerService(){
		this.managerRepo = new ExpenseManagerWebsiteRepositoryImpl();
	}
	
	
	
	ExpenseWebsiteRepositoryImpl employeeDao = new ExpenseWebsiteRepositoryImpl();
	
	public boolean isValidUser(String email, String password) {
		List<Employees> employee = employeeDao.viewAllEmployees();
		
		for(Employees i: employee) {
			if(i.getEmail().equals(email) && i.getPassword().equals(password)) {
				int id = i.getEmployeeId();
				setEmployeeId(id);
				System.out.println(id);
				if(checkManager() != null) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public List<Manager> checkManager(){
		List<Manager> manager = new ArrayList<>();
		manager = managerRepo.checkForManager(getEmployeeId());
		for(Manager i: manager) {
			if(i.getEmployeeId() == getEmployeeId() && i.getDesignation().equals("manager") || i.getDesignation().equals("general manager")) {
			System.out.println(manager);
				return manager;
			}
		}
		return null;
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
	
	public void approveDenyRequest(int expenseId, String decision, int employeeId2){
		String name1 = getManagerLastName(employeeId2);
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
