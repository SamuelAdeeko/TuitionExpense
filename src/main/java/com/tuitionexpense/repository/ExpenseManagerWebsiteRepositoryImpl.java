package com.tuitionexpense.repository;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Transaction; 

import org.hibernate.Session;

import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;
import com.tuitionexpense.model.Manager;
import com.tuitionexpense.service.ExpenseWebsiteManagerService;
import com.tuitionexpense.util.HibernateSessionFactory;

public class ExpenseManagerWebsiteRepositoryImpl implements ExpenseManagerWebsiteRepository {

	
	public ExpenseManagerWebsiteRepositoryImpl() {
		System.out.println("manager impl");
	}
	
	@Override
	public int managerLogin(String email, String password) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void approveDenyRequest(int expenseId, String decision, String signature) {
//		ExpenseWebsiteManagerService managerSign = new ExpenseWebsiteManagerService();
//		String signature = managerSign.getManagerLastName(employeeId);
//		System.out.println(signature);
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Expense expenseUpdate = s.load(Expense.class, expenseId);
			expenseUpdate.setStatus(decision);
			expenseUpdate.setAuthorizedBy(signature);
			s.update(expenseUpdate);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
	}
		
		
	// View employee pending request by manager
	@Override
	public List<Expense> viewPendingRequest(int managerId) {
		List<Expense> pendingRequest = new ArrayList<>();
		// An Array list of all employees managed by a Manager 
		List<Integer> employeeId = new ExpenseWebsiteManagerService().getManagedEmployeeId(managerId);
		int counter = 0;
		// An ArrayList of the Query is stored in newPend and returned
		List<Expense> newPend = new ArrayList<>();
		
		Session s = null;
		Transaction tx = null;
		
		for(int i : employeeId) {
			counter = i;
			
			try {
				s = HibernateSessionFactory.getSession();
				tx = s.beginTransaction();
				pendingRequest = s.createQuery("FROM Expense WHERE employee_id = :employeeId AND status = 'pending'", Expense.class).setParameter("employeeId", counter).getResultList();
				newPend.addAll(pendingRequest);
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			}finally {
				s.close();
			}
		}
			
		return newPend;
	}
	
	@Override
	public List<Expense> viewAllExpenseRequest(int managerId) {
		List<Expense> allExpenseRequest = new ArrayList<>();
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			allExpenseRequest = s.createQuery("FROM Expense", Expense.class).getResultList();
		
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}

		return allExpenseRequest ;
		
	}

	
	@Override
	public List<Expense> viewImagesOfAllReceipts(long managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expense> viewAllResolvedRequest(long managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employees> viewEmployeesAndManagers(long managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expense viewRequestFromManagedEmployee(int employeeId) {
		Expense requestView = null;
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			requestView = s.get(Expense.class, employeeId);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		return requestView;
	}

	@Override
	public Employees registerEmployee(long userId, Employees employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> checkForManager(int employeeId) {
		List<Manager> manager = new ArrayList<>();
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			manager = s.createQuery("FROM Manager WHERE employee_id = :employeeId AND designation = 'manager'", Manager.class).setParameter("employeeId", employeeId).getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		return manager;
	}


	@Override
	public List<Employees> allEmployeesManaged(int managerId) {
		List<Employees> allEmployeesManaged = null;
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			allEmployeesManaged = s.createQuery("FROM Employees WHERE manager_id = :managerId", Employees.class).setParameter("managerId", managerId).getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		
		
		return allEmployeesManaged;
	}

}
