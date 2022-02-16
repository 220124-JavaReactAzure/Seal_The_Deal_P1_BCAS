package com.revature.sealTheDeal.models;

public class Employee extends User {

	private User user;
	private String employeeId;
	private boolean isAccountTaken;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
