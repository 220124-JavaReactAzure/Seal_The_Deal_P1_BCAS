package com.revature.sealTheDeal.servlets.employee;

import javax.servlet.http.HttpServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.services.EmployeeServices;

public class AddFloristsServlet extends HttpServlet{
	
	EmployeeServices employeeServices;
	ObjectMapper mapper;
	
	public AddFloristsServlet(EmployeeServices employeeServices, ObjectMapper mapper) {
		this.employeeServices = employeeServices;
		this.mapper = mapper;
	}


}
