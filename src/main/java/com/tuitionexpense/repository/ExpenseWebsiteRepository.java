package com.tuitionexpense.repository;

import java.util.List;

import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;

public interface ExpenseWebsiteRepository {

	// testing employees login out with return object
	public List<Employees> viewAllEmployees();
	public void submitReimbursementRequest(Expense request);
	public List<Expense> viewPendingReimbursement(String userEmail);
	public List<Expense> viewResolvedReimbursement(String userEmail);
	public Employees viewPersonalInformation(int id);
	public int updatePersonalInformation(String userEmail, String maritalStatus);
	public String sendNotificationEmail(String email);
	public void createEmployee(Employees employee);
	

}
