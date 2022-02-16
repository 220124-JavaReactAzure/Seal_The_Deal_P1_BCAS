package com.revature.sealTheDeal.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.dao.EmployeeDAO;
import com.revature.sealTheDeal.services.EmployeeServices;

import com.revature.sealTheDeal.servlets.employee.EmployeeHomeServlet;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ObjectMapper mapper = new ObjectMapper();

		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeServices employeeServices = new EmployeeServices(employeeDAO);
		EmployeeHomeServlet employeeHomeServlet = new EmployeeHomeServlet(employeeServices, mapper);

		ServletContext context = sce.getServletContext();
		context.addServlet("EmployeeHomeServlet", employeeHomeServlet).addMapping("/employees/*");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

}
