package com.revature.sealTheDeal.services;


import java.util.List;

import com.revature.sealTheDeal.dao.EmployeeDAO;
import com.revature.sealTheDeal.models.Booking;
import com.revature.sealTheDeal.models.Employee;

public class EmployeeServices {

	private final EmployeeDAO employeeDAO;
	Employee sessionEmployee = null;

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
	
	public void setSessionEmployee(Employee currentEmployee) {
		sessionEmployee = currentEmployee;
	}
	
	public Employee getSessionEmployee() {
		return sessionEmployee;
	}
	
	public void closeSessionEmployee() {
		sessionEmployee = null;
	}

	public void addWeddingDay(String weddingDay) {
		employeeDAO.addWeddingDay(weddingDay);
		
	}
	
	public Boolean CheckNumeric(String userInput) {
		for(int i = 0; i<userInput.length(); i++) {
			if(!(Character.isDigit(userInput.charAt(i)))){
				if(userInput.charAt(i) != '.') {
					return true;
				}
			}
		}
		return false;
	}
	
	public List<Booking> getByService(int serviceType, String weddingDay){
		return employeeDAO.getByService(serviceType, weddingDay);
	}

	public Booking getBookedService(String bookedMusician, String weddingDay) {
		return employeeDAO.getBookedService(bookedMusician, weddingDay);
	}

	public void updateBooking(Booking updateService, String weddingDay) {
		employeeDAO.updateBooking(updateService, weddingDay);
		
	}
	
}