package com.revature.sealTheDeal.servlets.weddingUser;

import javax.servlet.http.HttpServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.services.EmployeeServices;
import com.revature.sealTheDeal.services.WeddingUserServices;

public class ChoosePhotographersServlet extends HttpServlet {
	
	WeddingUserServices weddingUserServices;
	EmployeeServices employeeServices;
	ObjectMapper mapper;
	public ChoosePhotographersServlet(EmployeeServices employeeServices, WeddingUserServices weddingUserServices, ObjectMapper mapper) {
		this.employeeServices = employeeServices;
		this.weddingUserServices = weddingUserServices;
		this.mapper = mapper;
	}

}
