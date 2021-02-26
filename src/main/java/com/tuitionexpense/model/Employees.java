package com.tuitionexpense.model;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity     // marks this class as an entity, meaning that we intend to map this class to a table
@Table(name = "employees", schema = "expenses")
public class Employees {
	@Id   // used to denote that it is a primary key on this table
	@Column(name = "employee_id")
//	@GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
//	@SequenceGenerator(allocationSize = 1,  name = "employee_id_seq", sequenceName = "employee_id_seq")
	
	private int employeeId;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "position")
	private String position;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "dob")
	private Date dob;
	@Column(name = "marital_status")
	private String maritalStatus;
	
	
	
	public Employees() {
		super();
	
	}


	public Employees(int employeeId, String email, String password, String position, String firstName, String lastName,
			Date dob, String maritalStatus) {
		super();
		this.employeeId = employeeId;
		this.email = email;
		this.password = password;
		this.position = position;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.maritalStatus = maritalStatus;
		
	}


	public long getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employees other = (Employees) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maritalStatus == null) {
			if (other.maritalStatus != null)
				return false;
		} else if (!maritalStatus.equals(other.maritalStatus))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", email=" + email + ", password=" + password + ", position="
				+ position + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", maritalStatus="
				+ maritalStatus + "]";
	}


	


}
