package com.revature.sealTheDeal.servlets.weddingUser;

import javax.servlet.http.HttpServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.services.EmployeeServices;
import com.revature.sealTheDeal.services.WeddingUserServices;

public class ChooseCaterersServlet extends HttpServlet  {
	
	WeddingUserServices weddingUserServices;
	EmployeeServices employeeServices;
	ObjectMapper mapper;
	public ChooseCaterersServlet(EmployeeServices employeeServices, WeddingUserServices weddingUserServices, ObjectMapper mapper) {
		this.employeeServices = employeeServices;
		this.weddingUserServices = weddingUserServices;
		this.mapper = mapper;
	}

}
