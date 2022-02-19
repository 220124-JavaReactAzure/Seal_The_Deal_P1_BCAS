package com.revature.sealTheDeal.services;


import java.util.List;

import com.revature.sealTheDeal.dao.EmployeeDAO;
import com.revature.sealTheDeal.models.Booking;
import com.revature.sealTheDeal.models.Employee;

public class EmployeeServices {

	private final EmployeeDAO employeeDAO;

	public EmployeeServices(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public boolean addEmployee(Employee employee) {
		return employeeDAO.addEmployee(employee);
	}
	
	public boolean addBooking(Booking booking) {
		return employeeDAO.addBooking(booking);
	}
	public boolean verifyByEmployeeID(String employeeID) {
		Employee testEmployee = employeeDAO.getEmployeeByEmployeeID(employeeID);
		if(testEmployee == null) {
			return true;
		}else {
			if(testEmployee.isAccountTaken()) {
				return true;
			}else {
				return false;
			}
		}
	}

	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	public Employee getEmployeeByUsername(String username) {

		return employeeDAO.getEmployeeByUsername(username);
	}

	public void updateEmployeeWithSessionMethod(Employee employee) {
		employeeDAO.updateEmployeeWithSessionMethod(employee);
	}
	
	public void deleteByEmployeeID(String employeeID) {
		employeeDAO.deleteByEmployeeID(employeeID);
	}

	public void updateEmployeeWithHQL(Employee employee) {
		employeeDAO.updateEmployeeWithHQL(employee);
	}

	public boolean getByServiceName(String serviceName) {
		// TODO Auto-generated method stub
		return employeeDAO.getByServiceName(serviceName);
	}
}