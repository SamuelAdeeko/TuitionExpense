package com.tuitionexpense.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity     // marks this class as an entity, meaning that we intend to map this class to a table
@Table(name = "manager", schema = "expenses")

public class Manager {

	@Column(name = "designation")
	private String designation;
	@Id 
	@Column(name = "department_id")
	@JoinColumn
	private int departmentId;
	@Column(name = "employee_id")
	@JoinColumn
	private int employeeId;
	@Column(name = "department_name")
	private String departmentName;
	
	
	public Manager() {
		super();
		
	}


	public Manager(String designation, int departmentId, int employeeId) {
		super();
		this.designation = designation;
		this.departmentId = departmentId;
		this.employeeId = employeeId;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + departmentId;
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + employeeId;
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
		Manager other = (Manager) obj;
		if (departmentId != other.departmentId)
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (employeeId != other.employeeId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Manager [designation=" + designation + ", departmentId=" + departmentId + ", employeeId=" + employeeId
				+ "]";
	}

	
	
	
}
