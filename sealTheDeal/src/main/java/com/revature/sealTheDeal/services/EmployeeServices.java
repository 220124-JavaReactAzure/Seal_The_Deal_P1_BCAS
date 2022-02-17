package com.revature.sealTheDeal.services;


import java.util.List;

import com.revature.sealTheDeal.dao.EmployeeDAO;
import com.revature.sealTheDeal.models.Employee;

public class EmployeeServices {

	private final EmployeeDAO employeeDAO;

	public EmployeeServices(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public boolean addEmployee(Employee employee) {
		return employeeDAO.addEmployee(employee);
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

	public void updateEmployeeWithHQL(Employee employee) {
		employeeDAO.updateEmployeeWithHQL(employee);
	}
}