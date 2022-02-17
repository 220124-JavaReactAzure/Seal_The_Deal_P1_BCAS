package com.revature.sealTheDeal.servlets.registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.dao.EmployeeDAO;
import com.revature.sealTheDeal.models.Employee;
import com.revature.sealTheDeal.services.EmployeeServices;
import com.revature.sealTheDeal.services.UserServices;

//@WebServlet("/registration/employee/")
public class RegisterEmployeeServlet extends HttpServlet{
	
	String employeeID = null;
	String firstName = null;
	String lastName = null;
	String email = null;
	String username = null;
	String password = null;
	String passwordVerify = null;
	String message = null;
	
	UserServices userServices;
	EmployeeServices employeeServices;
	ObjectMapper mapper;
	
	
	public RegisterEmployeeServlet(UserServices userServices, EmployeeServices employeeServices, ObjectMapper mapper) {
		this.userServices = userServices;
		this.employeeServices = employeeServices;
		this.mapper = mapper;
	}




	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
	    
	    out.println("<h3>Register New Employee</h3>");
	    if(message != null) {
	    	out.println("<p style=\"color:red;\">"+message+"</p>");
	    }
	    out.println("<HTML>"
	    		+ "<BODY>"
	    		+ "<FORM METHOD=POST>Employee ID:"
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"employee_id\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>First Name:"
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"first_name\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Last Name:"
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"last_name\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Email:"
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"email\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Username:"
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"username\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Password:"
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=PASSWORD NAME=\"password\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Re-Enter Password: "
	    		+ "<INPUT TYPE=PASSWORD NAME=\"verify_password\">"
	    		+ "<P>"
	    		+ "<INPUT TYPE=SUBMIT>"
	    		+ "</FORM>"
	    		+ "</BODY>"
	    		+ "</HTML>");
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		employeeID = req.getParameter("employee_id");
		firstName = req.getParameter("first_name");
		lastName = req.getParameter("last_name");
		email = req.getParameter("email");
		username = req.getParameter("username");
		password = req.getParameter("password");
		passwordVerify = req.getParameter("verify_password");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		if(employeeID.trim().isEmpty() || firstName.trim().isEmpty() || lastName.trim().isEmpty() || email.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || passwordVerify.trim().isEmpty()) {
			message = "ALL FIELDS MUST BE FILLED TO REGISTER";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/employee/\">");
		}
		else if(Character.isLowerCase(firstName.trim().charAt(0)) || Character.isLowerCase(lastName.trim().charAt(0))) {
			message = "THE FIRST LETTER OF YOUR FIRST AND LAST NAME MUST BE CAPITAL";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/employee/\">");
		}
		else if(!(password.equals(passwordVerify))) {
			message = "PASSWORDS MUCH MATCH";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/employee/\">");
		}
		else if(userServices.getByUsername(username.trim())) {
			message = "USERNAME ALREADY EXISTS";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/employee/\">");
		}
		else if(userServices.getByEmail(email.trim()) ) {
			message = "EMAIL ALREADY EXISTS";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/employee/\">");
		}
		
		
		//else if( checking employeeid exists )
		//else if( checking employeeid is not taken )
		else{
			Employee newEmployee = new Employee(username,firstName,lastName,password,email,1,employeeID,true);
			EmployeeDAO empDAO = new EmployeeDAO();
			EmployeeServices empServ = new EmployeeServices(empDAO);
			empServ.addEmployee(newEmployee);
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/\">");
		}
		
		
	}

}
