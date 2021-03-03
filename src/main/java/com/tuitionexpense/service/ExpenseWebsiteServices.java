package com.tuitionexpense.service;

import java.util.ArrayList;
import java.util.List;

import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;
import com.tuitionexpense.repository.ExpenseWebsiteRepository;
import com.tuitionexpense.repository.ExpenseWebsiteRepositoryImpl;

public class ExpenseWebsiteServices {
	
	private ExpenseWebsiteRepository expenseWebsiteRepository;
	
	public ExpenseWebsiteServices() {
		expenseWebsiteRepository = new ExpenseWebsiteRepositoryImpl();
		}

	//ExpenseWebsiteRepository dao = new ExpenseWebsiteRepositoryImpl();
	
	// Note... as long as I can get employeeId after login, i should be able to implement all the methods
	
	
	
	public boolean isValidUser(String email, String password, int employeeId) {
		List<Employees> employee = this.expenseWebsiteRepository.viewAllEmployees();
		
		for(Employees i: employee) {
			if(i.getEmail().equals(email) && i.getPassword().equals(password) && i.getEmployeeId() == employeeId) {
	
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
	
	public Employees viewPersonalInformation(int employeeId) {
		
		return this.expenseWebsiteRepository.viewPersonalInformation(employeeId);
	}
	
	public List<Expense> viewEmployeePendingExpense(int employeeId) {
		List<Expense> expense = new ArrayList<>();
		expense = this.expenseWebsiteRepository.viewPendingReimbursement(employeeId);

	return expense;
	}
	
	public List<Expense> viewResolvedReimbursement(int employeeId){
		List<Expense> resolvedExpense = new ArrayList<>();
		resolvedExpense = this.expenseWebsiteRepository.viewResolvedReimbursement(employeeId);
		
		return resolvedExpense;
	}
	
	public void updateMaritalStatus(String maritalStatus, int employeeId) {
		this.expenseWebsiteRepository.updatePersonalInformation(employeeId, maritalStatus);
	}
}
