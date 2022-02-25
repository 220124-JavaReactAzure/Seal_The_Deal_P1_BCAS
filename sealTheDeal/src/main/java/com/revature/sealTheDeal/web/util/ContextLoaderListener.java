package com.revature.sealTheDeal.web.util;

import java.io.File;

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
import com.revature.sealTheDeal.servlets.HomeServlet;
import com.revature.sealTheDeal.servlets.employee.AddCaterersServlet;
import com.revature.sealTheDeal.servlets.employee.AddFloristsServlet;
import com.revature.sealTheDeal.servlets.employee.AddMusiciansServlet;
import com.revature.sealTheDeal.servlets.employee.AddPhotographersServlet;
import com.revature.sealTheDeal.servlets.employee.AddVenuesServlet;
import com.revature.sealTheDeal.servlets.employee.EmployeeHomeServlet;
import com.revature.sealTheDeal.servlets.guest.ChooseMealServlet;
import com.revature.sealTheDeal.servlets.guest.ConfirmAttendanceServlet;
import com.revature.sealTheDeal.servlets.guest.GuestHomeServlet;
import com.revature.sealTheDeal.servlets.guest.ViewWeddingDetailsServlet;
import com.revature.sealTheDeal.servlets.registration.RegisterEmployeeServlet;
import com.revature.sealTheDeal.servlets.registration.RegisterGuestServlet;
import com.revature.sealTheDeal.servlets.registration.RegisterWeddingUserServlet;
import com.revature.sealTheDeal.servlets.weddingUser.BudgetServlet;
import com.revature.sealTheDeal.servlets.weddingUser.ChooseCaterersServlet;
import com.revature.sealTheDeal.servlets.weddingUser.ChooseFloristsServlet;
import com.revature.sealTheDeal.servlets.weddingUser.ChooseMusiciansServlet;
import com.revature.sealTheDeal.servlets.weddingUser.ChoosePhotographersServlet;
import com.revature.sealTheDeal.servlets.weddingUser.ChooseVenuesServlet;
import com.revature.sealTheDeal.servlets.weddingUser.CreateGiftRegistry;
import com.revature.sealTheDeal.servlets.weddingUser.ViewYourWeddingDetailsServlet;
import com.revature.sealTheDeal.servlets.weddingUser.WeddingUserHomeServlet;

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
		
		//home page
		HomeServlet homeServlet = new HomeServlet(userServices, employeeServices, guestServices, weddingUserServices, mapper);
		//registration pages
		RegisterEmployeeServlet registerEmployeeServlet = new RegisterEmployeeServlet(userServices, employeeServices, mapper);
		RegisterGuestServlet registerGuestServlet = new RegisterGuestServlet(userServices, weddingUserServices, guestServices, mapper);
		RegisterWeddingUserServlet registerWeddingUserServlet = new RegisterWeddingUserServlet(userServices, weddingUserServices, employeeServices, mapper);
		//employee services pages
		EmployeeHomeServlet employeeHomeServlet = new EmployeeHomeServlet(employeeServices, mapper);
		AddCaterersServlet addCaterersServlet = new AddCaterersServlet(employeeServices, mapper);
		AddFloristsServlet addFloristsServlet = new AddFloristsServlet(employeeServices, mapper);
		AddMusiciansServlet addMusiciansServlet = new AddMusiciansServlet(employeeServices, mapper);
		AddVenuesServlet addVenuesServlet = new AddVenuesServlet(employeeServices, mapper);
		AddPhotographersServlet addPhotographersServlet = new AddPhotographersServlet(employeeServices, mapper);
		//wedding user services pages
		WeddingUserHomeServlet weddingUserHomeServlet = new WeddingUserHomeServlet(weddingUserServices, mapper);
		BudgetServlet budgetServlet = new BudgetServlet(weddingUserServices, mapper);
		ChooseCaterersServlet chooseCaterersServlet = new ChooseCaterersServlet(employeeServices, weddingUserServices, mapper);
		ChooseFloristsServlet chooseFloristsServlet = new ChooseFloristsServlet(employeeServices, weddingUserServices, mapper);
		ChooseMusiciansServlet chooseMusiciansServlet = new ChooseMusiciansServlet(employeeServices, weddingUserServices, mapper);
		ChoosePhotographersServlet choosePhotographersServlet = new ChoosePhotographersServlet(employeeServices, weddingUserServices, mapper);
		ChooseVenuesServlet chooseVenuesServlet = new ChooseVenuesServlet(employeeServices, weddingUserServices, mapper);
		CreateGiftRegistry createGiftRegistry = new CreateGiftRegistry(weddingUserServices, mapper);
		ViewYourWeddingDetailsServlet viewYourWeddingDetailsServlet = new ViewYourWeddingDetailsServlet(weddingUserServices, mapper);
		//guest services pages
		GuestHomeServlet guestHomeServlet = new GuestHomeServlet(guestServices, mapper);
		ChooseMealServlet chooseMealServlet = new ChooseMealServlet(guestServices, mapper);
		ConfirmAttendanceServlet confirmAttendanceServlet = new ConfirmAttendanceServlet(guestServices, weddingUserServices, mapper);
		ViewWeddingDetailsServlet viewWeddingDetailsServlet = new ViewWeddingDetailsServlet(guestServices, weddingUserServices, mapper);
		
		
		ServletContext context = sce.getServletContext();
		
		//home page
		context.addServlet("HomeServlet", homeServlet).addMapping("/");
		//registration pages
		context.addServlet("RegisterEmployeeServlet", registerEmployeeServlet).addMapping("/registration/employee/");
		context.addServlet("RegisterGuestServlet", registerGuestServlet).addMapping("/registration/guest/");
		context.addServlet("RegisterWeddingUserServlet", registerWeddingUserServlet).addMapping("/registration/weddingUser/");
		//employee services pages
		context.addServlet("EmployeeHomeServlet", employeeHomeServlet).addMapping("/employeeHome/");
		context.addServlet("AddCaterersServlet", addCaterersServlet).addMapping("/employeeHome/addCaterers/");
		context.addServlet("AddFloristsServlet", addFloristsServlet).addMapping("/employeeHome/addFlorists/");
		context.addServlet("AddMusiciansServlet", addMusiciansServlet).addMapping("/employeeHome/addMusicians/");
		context.addServlet("AddPhotographersServlet", addPhotographersServlet).addMapping("/employeeHome/addPhotographers/");
		context.addServlet("AddVenuesServlet", addVenuesServlet).addMapping("/employeeHome/addVenues/");
		//wedding user services pages
		context.addServlet("WeddingUserHomeServlet", weddingUserHomeServlet).addMapping("/weddingUserHome/");
		context.addServlet("BudgetServlet", budgetServlet).addMapping("/weddingUserHome/budget/");
		context.addServlet("ChooseCaterersServlet", chooseCaterersServlet).addMapping("/weddingUserHome/chooseCaterers/");
		context.addServlet("ChooseFloristsServlet", chooseFloristsServlet).addMapping("/weddingUserHome/chooseFlorists/");
		context.addServlet("ChooseMusiciansServlet", chooseMusiciansServlet).addMapping("/weddingUserHome/chooseMusicians/");
		context.addServlet("ChoosePhotographersServlet", choosePhotographersServlet).addMapping("/weddingUserHome/choosePhotographers/");
		context.addServlet("ChooseVenuesServlet", chooseVenuesServlet).addMapping("/weddingUserHome/chooseVenues/");
		context.addServlet("CreateGiftRegistry", createGiftRegistry).addMapping("/weddingUserHome/giftRegistry/");
		context.addServlet("ViewYourWeddingDetailsServlet", viewYourWeddingDetailsServlet).addMapping("/weddingUserHome/viewYourWeddingDetails/");
		//guest services pages
		context.addServlet("ConfirmAttendanceServlet", confirmAttendanceServlet).addMapping("/guestHome/confirmAttendance/");
		context.addServlet("GuestHomeServlet", guestHomeServlet).addMapping("/guestHome/");
		context.addServlet("ChooseMealServlet", chooseMealServlet).addMapping("/guestHome/chooseMeal/");
		context.addServlet("ViewWeddingDetails", viewWeddingDetailsServlet).addMapping("/guestHome/viewWeddingDetails/");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

}
