package com.revature.sealTheDeal.servlets.registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.services.EmployeeServices;
import com.revature.sealTheDeal.services.GuestServices;
import com.revature.sealTheDeal.services.UserServices;
import com.revature.sealTheDeal.services.WeddingUserServices;


public class RegisterGuestServlet extends HttpServlet{
	
	String weddingName = null;
	String firstName = null;
	String lastName = null;
	String email = null;
	String username = null;
	String password = null;
	String passwordVerify = null;
	String message = null;
	
	UserServices userServices;
	WeddingUserServices weddingUserServices;
	GuestServices guestServices;
	ObjectMapper mapper;
	
	public RegisterGuestServlet(UserServices userServices, WeddingUserServices weddingUserServices,
			GuestServices guestServices, ObjectMapper mapper) {
		this.userServices = userServices;
		this.weddingUserServices = weddingUserServices;
		this.guestServices = guestServices;
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
	    		+ "<FORM METHOD=POST>Wedding Party Name: "
	    		+ "<INPUT TYPE=TEXT NAME=\"wedding_id\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>First Name: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"first_name\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Last Name: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"last_name\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Email: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"email\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Username: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"username\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Password: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=PASSWORD NAME=\"password\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Re-Enter Password: "
	    		+ "&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=PASSWORD NAME=\"verify_password\">"
	    		+ "<P>"
	    		+ "<INPUT TYPE=SUBMIT>"
	    		+ "</FORM>"
	    		+ "</BODY>"
	    		+ "</HTML>");
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
