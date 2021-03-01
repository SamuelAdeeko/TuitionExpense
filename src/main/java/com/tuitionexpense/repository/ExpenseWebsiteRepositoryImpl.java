package com.tuitionexpense.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.tuitionexpense.model.Employees;
import com.tuitionexpense.model.Expense;
import com.tuitionexpense.util.HibernateSessionFactory;

public class ExpenseWebsiteRepositoryImpl implements ExpenseWebsiteRepository {


	@Override
	public List<Employees> viewAllEmployees() {
		List<Employees> employee = new ArrayList<>();
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			employee = s.createQuery("FROM Employees", Employees.class).getResultList();
		
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}

		return employee;
		
	}

	@Override
	public void submitReimbursementRequest(Expense request) {
		
		// All of our work is done within the context of a Hibernate session
		Session s = null;
		
		// The Transaction Interface gives you control over your database transactions
		// you can use it to rollback changes, commit changes and begin transactions
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			// this method persist the request, i.e creates a new record
			s.save(request);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			// closes the session
			s.close();
		}
		
	}

	@Override
	public List<Expense> viewPendingReimbursement(int employeeId) {
		List<Expense> expense = new ArrayList<>();
		
		Session s = null;
		Transaction tx = null;
		
//		String hql = "FROM Expense status WHERE status.id = pending";
//		Query query = session.createQuery(hql);
//		List results = query.list();
		
//		rList = s.createQuery("FROM Reimbursement WHERE employeeId = :employeeId
//		AND status = 'pending'", Reimbursement.class).setParameter("employeeId", employeeId).getResultList();
//		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			expense = s.createQuery("FROM Expense WHERE employee_id = :employeeId AND status = 'pending'", Expense.class).setParameter("employeeId", employeeId).getResultList();
		
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		return expense;
	}

	@Override
	public List<Expense> viewResolvedReimbursement(int employeeId) {
		List<Expense> resolvedExpense = new ArrayList<>();
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			resolvedExpense = s.createQuery("FROM Expense WHERE employee_id = :employeeId AND status = 'approved'", Expense.class).setParameter("employeeId", employeeId).getResultList();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		
		
		return resolvedExpense;
	}

	@Override
	public Employees viewPersonalInformation(int id) {
		Employees employee = null;
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			employee = s.get(Employees.class, id);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		return employee;
	}

	@Override
	public void updatePersonalInformation(int employeeId, String maritalStatus) {
		
	//	Employees employee = null;
		Session s = null;
		Transaction tx = null;
		
		try {
			// get connection session
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Employees employee = s.load(Employees.class, employeeId);
			employee.setMaritalStatus(maritalStatus);
			s.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			
		} finally {
			s.close();
		}
//		Employee employee = s.load(Employee.class, employeeId);
//		log.info(employee);
//		employee.setPhone(phone);
//		s.update(employee);
		
	
	}

	@Override
	public String sendNotificationEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	

	
	
//	@Override
//	public Employees employeeLogin(String email, String password) {
//		Employees employee = null;
//		
//		try (Connection connection = PostgresqlConnection.getConnection()){
//			final String sql = "SELECT employee_id, email, password, position, first_name, last_name, dob FROM expenses.employees WHERE email = ? AND password = ?";
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, "email");
//			preparedStatement.setString(2, "password");
//			ResultSet resultSet = preparedStatement.executeQuery();
//			if(resultSet.next()) {
//				employee = new Employees();
//				employee.setEmployeeId(resultSet.getLong("employeeId"));
//				employee.setEmail(resultSet.getString("email"));
//				employee.setPassword(resultSet.getString("password"));
//				employee.setPosition(resultSet.getString("position"));
//				employee.setFirstName(resultSet.getString("first_name"));
//				employee.setLastName(resultSet.getString("last_name"));
//				employee.setDob(resultSet.getDate("dob").toLocalDate());
//				
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return employee;
//	}

	
//
//	@Override
//	public void submitReimbursementRequest(Expenses request) {
//		
//		
//		try(Connection connection = PostgresqlConnection.getConnection()) {
//			final String sql = "INSERT INTO expenses.expenses (expense_id, employee_id, amount, authorized_by, comments, details, expense_date, expense_image, status) VALUES (default,?,?,?,?,?,?,?,?)";
//			preparedStatement = connection.prepareStatement(sql);
//		//	preparedStatement.setLong(1, request.getExpenseId());
//			preparedStatement.setLong(1, request.getEmployeeId());
//			preparedStatement.setLong(2,request.getAmount());
//			preparedStatement.setString(3, request.getAuthorizedBy());
//			preparedStatement.setString(4, request.getAuthorizedBy());
//			preparedStatement.setString(5, request.getDetails());
//			preparedStatement.setDate(6, Date.valueOf(request.getExpenseDate()));
//			preparedStatement.setString(7, request.getExpenseImage());
//			preparedStatement.setString(8, request.getStatus());
//			preparedStatement.executeUpdate();
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//	}

	@Override
	public void createEmployee(Employees employee) {
	
	
	Session s = null;
	Transaction tx = null;
	
	try {
		s = HibernateSessionFactory.getSession();
		tx = s.beginTransaction();
		s.save(employee);
		tx.commit();
	} catch (Exception e) {
		e.printStackTrace();
		tx.rollback();
	} finally {
		s.close();
	}
	
	
	
//		int c = 0;
//		
//		try(Connection connection = PostgresqlConnection.getConnection()) {
//			final String sql = "INSERT INTO expenses.employees (employee_id, email, password, position, first_name, last_name, dob ) VALUES (default,?,?,?,?,?,?) ";
//			preparedStatement = connection.prepareStatement(sql);
//		//	preparedStatement.setLong(1, employee.getEmployeeId());
//			preparedStatement.setString(1, employee.getEmail());
//			preparedStatement.setString(2, employee.getPassword());
//			preparedStatement.setString(3, employee.getPosition());
//			preparedStatement.setString(4, employee.getFirstName());
//			preparedStatement.setString(5, employee.getLastName());
//			preparedStatement.setDate(6, Date.valueOf(employee.getDob()));
//			if(preparedStatement.executeUpdate() != 0) {
//				c = preparedStatement.executeUpdate();
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return c;
	}

	@Override
	public void getEmployeeId(String email, String password) {
		
		
		
	}

}
