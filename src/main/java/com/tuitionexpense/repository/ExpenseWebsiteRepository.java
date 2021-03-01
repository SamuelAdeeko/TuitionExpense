package com.tuitionexpense.repository;

import java.util.List;

import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;

public interface ExpenseWebsiteRepository {

	// testing employees login out with return object
	public List<Employees> viewAllEmployees();
	public void submitReimbursementRequest(Expense request);
	public List<Expense> viewPendingReimbursement(int employeeId);
	public List<Expense> viewResolvedReimbursement(int employeeId);
	public Employees viewPersonalInformation(int id);
	public void updatePersonalInformation(int employeeId, String maritalStatus);
	public String sendNotificationEmail(String email);
	public void createEmployee(Employees employee);
	public void getEmployeeId(String email, String password);
	
	

}
