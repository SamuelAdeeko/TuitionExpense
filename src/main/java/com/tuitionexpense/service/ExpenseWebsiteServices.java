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
	
	
	public boolean isValidUser(String email, String password) {
		List<Employees> employee = this.expenseWebsiteRepository.viewAllEmployees();
		
		for(Employees i: employee) {
			if(i.getEmail().equals(email) && i.getPassword().equals(password)) {
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
	
	public Employees viewPersonalInformation(int id) {
		return this.expenseWebsiteRepository.viewPersonalInformation(id);
	}
}
