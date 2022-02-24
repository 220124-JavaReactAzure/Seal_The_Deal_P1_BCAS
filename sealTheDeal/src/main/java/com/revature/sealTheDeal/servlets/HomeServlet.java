package com.revature.sealTheDeal.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.Employee;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.models.User;
import com.revature.sealTheDeal.models.WeddingUser;
import com.revature.sealTheDeal.services.EmployeeServices;
import com.revature.sealTheDeal.services.GuestServices;
import com.revature.sealTheDeal.services.UserServices;
import com.revature.sealTheDeal.services.WeddingUserServices;


public class HomeServlet extends HttpServlet{

	String username = null;
	String password = null;
	String message = null;
	
	UserServices userServices;
	EmployeeServices employeeServices;
	GuestServices guestServices;
	WeddingUserServices weddingUserServices;
	ObjectMapper mapper;
	
	public HomeServlet(UserServices userServices, EmployeeServices employeeServices, GuestServices guestServices, WeddingUserServices weddingUserServices, ObjectMapper mapper) {
		this.userServices = userServices;
		this.employeeServices = employeeServices;
		this.guestServices = guestServices;
		this.weddingUserServices = weddingUserServices;
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
	    
	    out.println("<h2>Prepare To Seal The Deal</h2>");
	    if(message != null) {
	    	out.println("<p style=\"color:red;\">"+message+"</p>");
	    }
	    
	    out.println("<HTML>"
	    		+ "<BODY>"
	    		+ "<FORM METHOD=POST>Username: "
	    		+ "<INPUT TYPE=TEXT NAME=\"username\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Password: "
	    		+ "<INPUT TYPE=PASSWORD NAME=\"password\">"
	    		+ "<P>"
	    		+ "<INPUT TYPE=SUBMIT>"
	    		+ "</FORM>"
	    		+ "</BODY>"
	    		+ "</HTML>");
	    
	    
	    out.println("<form action=\"http://localhost:8080/sealTheDeal/registration/\">"
	    		+ "<input type=\"submit\" value=\"Registration\">"
	    		+ "</form>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		username = req.getParameter("username");
		password = req.getParameter("password"); 
		
		User verification = userServices.returnByUsername(username);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		if(username.trim().isEmpty() || password.trim().isEmpty()) {
			message = "ALL FIELDS MUST BE FILLED TO LOG IN";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/\">");
		}else if(verification == null) {
			message = "USERNAME OR PASSWORD IS INCORRECT";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/\">");
		}else if(!(password.equals(verification.getPass()))) {
			message = "USERNAME OR PASSWORD IS INCORRECT";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/\">");
		}else {
			switch (verification.getAccountType()) {
				case 1:
					Employee currentEmployee = employeeServices.getEmployeeByUsername(username);
					employeeServices.setSessionEmployee(currentEmployee);
					out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/employeeHome/\">");
					break;
				case 2:
					Guest currentGuest = guestServices.getGuestByUsername(username);
					guestServices.setSessionGuest(currentGuest);
					out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/guestHome/\">");
					break;
				case 3:
					WeddingUser currentWeddingUser = weddingUserServices.getWeddingUserByUsername(username);
					weddingUserServices.setSessionWeddingUser(currentWeddingUser);
					out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/\">");
					break;
				default:
					out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/\">");
					break;
			}
		}
	}
	
	
}
