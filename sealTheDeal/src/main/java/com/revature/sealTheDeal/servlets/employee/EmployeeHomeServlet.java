package com.revature.sealTheDeal.servlets.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.Employee;
import com.revature.sealTheDeal.services.EmployeeServices;

@SuppressWarnings("serial")
public class EmployeeHomeServlet extends HttpServlet {

	EmployeeServices employeeServices; 
	ObjectMapper mapper;
	
	
	
	public EmployeeHomeServlet(EmployeeServices employeeServices, ObjectMapper mapper) {
		this.employeeServices = employeeServices;
		this.mapper = mapper;
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Employee currentEmployee = employeeServices.getSessionEmployee();
		resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();
	    out.println("<style>"
	    		+ "body {"
	    		+ "background-image: url('https://ih1.redbubble.net/image.291419102.1980/st,small,507x507-pad,600x600,f8f8f8.u3.jpg');"
	    		+ "background-repeat: no-repeat;"
	    		+ "background-attachment: fixed;"
	    		+ "background-size: contain;"
	    		+ "background-position: center;"
	    		+ "background-color: grey;"
	    		+ "}"
	    		+ "</style>");
	    
	    out.print("<h3>Welcome Back " + currentEmployee.getFirstName() + "</h3>");
	    
	    out.println("<form action=\"http://localhost:8080/sealTheDeal/employeeHome/addCaterers/\">"
	    		+ "<input type=\"submit\" value=\"Add Caterer\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/employeeHome/addFlorists/\">"
	    		+ "<input type=\"submit\" value=\"Add Florist\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/employeeHome/addMusicians/\">"
	    		+ "<input type=\"submit\" value=\"Add Musician\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/employeeHome/addPhotographers/\">"
	    		+ "<input type=\"submit\" value=\"Add Photographer\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/employeeHome/addVenues/\">"
	    		+ "<input type=\"submit\" value=\"Add Venue\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/\">"
	    		+ "<input type=\"submit\" value=\"Logout\">"
	    		+ "</form>");
	}
	
    }

