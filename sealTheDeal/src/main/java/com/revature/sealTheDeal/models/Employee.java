package com.revature.sealTheDeal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "employeeinfo")
@PrimaryKeyJoinColumn(name = "username")
public class Employee extends User {
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "is_account_taken")
	private boolean isAccountTaken;
	
	public Employee() {
		super();
	}
	
	public Employee(String username, String firstName, String lastName, String pass, String user_email,
			int accountType, String employeeId, boolean isAccountTaken) {
		super(username, firstName, lastName, pass, user_email, accountType);
		this.employeeId = employeeId;
		this.isAccountTaken = isAccountTaken;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public boolean isAccountTaken() {
		return isAccountTaken;
	}
	public void setAccountTaken(boolean isAccountTaken) {
		this.isAccountTaken = isAccountTaken;
	}
}
