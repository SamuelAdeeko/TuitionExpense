package com.tuitionexpense.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;

import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;
import com.tuitionexpense.repository.ExpenseWebsiteRepository;
import com.tuitionexpense.repository.ExpenseWebsiteRepositoryImpl;

public class ExpenseWebsiteServices {
	
	private ExpenseWebsiteRepository expenseWebsiteRepository;
	private int employeeId ;
	
	
//	public class Example {
//	 
//	 private int number;   how can i assign a value to the class variable?
//	 
//	 public void sample(){
//	 	this.number = 1;     // this doesn't work
//	  }

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public ExpenseWebsiteServices() {
		expenseWebsiteRepository = new ExpenseWebsiteRepositoryImpl();
		}

	//ExpenseWebsiteRepository dao = new ExpenseWebsiteRepositoryImpl();
	
	// Note... as long as I can get employeeId after login, i should be able to implement all the methods
	
	
	
	public boolean isValidUser(String email, String password) {
		List<Employees> employee = this.expenseWebsiteRepository.viewAllEmployees();
		
		for(Employees i: employee) {
			if(i.getEmail().equals(email) && i.getPassword().equals(password)) {
				int id = i.getEmployeeId();
				setEmployeeId(id);
				System.out.println(id);
				return true;
			}
		}
		
		return false;
	}
	
	public List<Employees> viewAllEmployee(){
		List<Employees> employees = new ArrayList<>();
		employees = this.expenseWebsiteRepository.viewAllEmployees();
		
		
		return employees;
	}

	public void submitReimbursementRequest(Expense request) {
		
		this.expenseWebsiteRepository.submitReimbursementRequest(request);
	}
	
	public void createEmployee(Employees employee) {
		this.expenseWebsiteRepository.createEmployee(employee);
	}
	
	public Employees viewPersonalInformation() {
		
		return this.expenseWebsiteRepository.viewPersonalInformation(getEmployeeId());
	}
	
	public List<Expense> viewEmployeePendingExpense() {
		List<Expense> expense = new ArrayList<>();
		expense = this.expenseWebsiteRepository.viewPendingReimbursement(getEmployeeId());

	return expense;
	}
	
	public List<Expense> viewResolvedReimbursement(){
		List<Expense> resolvedExpense = new ArrayList<>();
		resolvedExpense = this.expenseWebsiteRepository.viewResolvedReimbursement(getEmployeeId());
		
		return resolvedExpense;
	}
	
	public void updateMaritalStatus(String maritalStatus) {
		this.expenseWebsiteRepository.updatePersonalInformation(getEmployeeId(), maritalStatus);
	}
}
