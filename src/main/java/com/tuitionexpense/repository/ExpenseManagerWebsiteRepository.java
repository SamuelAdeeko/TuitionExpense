package com.tuitionexpense.repository;

import java.util.List;

import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;
import com.tuitionexpense.model.Manager;

public interface ExpenseManagerWebsiteRepository {
	
	
	public int managerLogin(String email, String password);
	public void approveDenyRequest(int expenseId, String decision, String name);
	public List<Expense> viewPendingRequest(int managerId);
	public List<Expense> viewImagesOfAllReceipts(long managerId);
	public List<Expense> viewAllResolvedRequest(long managerId);
	public List<Employees> viewEmployeesAndManagers(long managerId);
	public Expense viewRequestFromManagedEmployee(int employeeId);
	public Employees registerEmployee(long userId, Employees employee);
	public List<Manager> checkForManager(int employeeId);
	public List<Employees> allEmployeesManaged(int managerId);
	public List<Expense> viewAllExpenseRequest(int manageId);
}
