package com.tuitionexpense.repository;

import java.util.List;

import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;

public interface ExpenseManagerWebsiteRepository {
	
	
	public int managerLogin(String email, String password);
	public void viewMangerHomePage(long managerId);
	public void managerLogout();
	public void approveDenyRequest(long managerId, String image);
	public List<Expense> viewPendingRequest(long managerId);
	public List<Expense> viewImagesOfAllReceipts(long managerId);
	public List<Expense> viewAllResolvedRequest(long managerId);
	public List<Employees> viewEmployeesAndManagers(long managerId);
	public List<Expense> viewRequestFromManagedEmployee(long managerId, long EmployeeId);
	public Employees registerEmployee(long userId, Employees employee);
}
