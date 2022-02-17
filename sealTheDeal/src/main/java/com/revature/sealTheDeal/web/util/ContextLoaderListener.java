package com.revature.sealTheDeal.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.dao.EmployeeDAO;
import com.revature.sealTheDeal.dao.GuestDAO;
import com.revature.sealTheDeal.dao.UserDAO;
import com.revature.sealTheDeal.dao.WeddingUserDAO;
import com.revature.sealTheDeal.services.EmployeeServices;
import com.revature.sealTheDeal.services.GuestServices;
import com.revature.sealTheDeal.services.UserServices;
import com.revature.sealTheDeal.services.WeddingUserServices;
import com.revature.sealTheDeal.servlets.employee.EmployeeHomeServlet;
import com.revature.sealTheDeal.servlets.registration.RegisterEmployeeServlet;
import com.revature.sealTheDeal.servlets.registration.RegisterGuestServlet;
import com.revature.sealTheDeal.servlets.registration.RegisterWeddingUserServlet;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ObjectMapper mapper = new ObjectMapper();

		UserDAO userDAO = new UserDAO();
		UserServices userServices = new UserServices(userDAO);
		
		GuestDAO guestDAO = new GuestDAO();
		GuestServices guestServices = new GuestServices(guestDAO);
		
		WeddingUserDAO weddingUserDAO = new WeddingUserDAO();
		WeddingUserServices weddingUserServices = new WeddingUserServices(weddingUserDAO);
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeServices employeeServices = new EmployeeServices(employeeDAO);
		
		
		RegisterEmployeeServlet registerEmployeeServlet = new RegisterEmployeeServlet(userServices, employeeServices, mapper);
		RegisterGuestServlet registerGuestServlet = new RegisterGuestServlet(userServices, weddingUserServices, guestServices, mapper);
		RegisterWeddingUserServlet registerWeddingUserServlet = new RegisterWeddingUserServlet(userServices, weddingUserServices, mapper);
		
		//EmployeeHomeServlet employeeHomeServlet = new EmployeeHomeServlet(employeeServices, mapper);

		ServletContext context = sce.getServletContext();
		context.addServlet("RegisterEmployeeServlet", registerEmployeeServlet).addMapping("/registration/employee/");
		context.addServlet("RegisterGuestServlet", registerGuestServlet).addMapping("/registration/guest/");
		context.addServlet("RegisterWeddingUserServlet", registerWeddingUserServlet).addMapping("/registration/weddingUser/");
		
		
		//context.addServlet("EmployeeHomeServlet", employeeHomeServlet).addMapping("/employees/*");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

}
