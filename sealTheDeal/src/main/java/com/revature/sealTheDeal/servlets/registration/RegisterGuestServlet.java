package com.revature.sealTheDeal.servlets.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.services.GuestServices;
import com.revature.sealTheDeal.services.UserServices;
import com.revature.sealTheDeal.services.WeddingUserServices;


@SuppressWarnings("serial")
public class RegisterGuestServlet extends HttpServlet{
	
	String weddingPartyName = null;
	String firstName = null;
	String lastName = null;
	String email = null;
	String username = null;
	String password = null;
	String passwordVerify = null;
	String message = null;
	
	private static final Logger LOGGER = Logger.getLogger(RegisterGuestServlet.class.getName());
	
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
	    
	    out.println("<h3>Register New Guest</h3>");
	    if(message != null) {
	    	out.println("<p style=\"color:red;\">"+message+"</p>");
	    }
	    out.println("<HTML>"
	    		+ "<BODY>"
	    		+ "<FORM METHOD=POST>Wedding Party Name: "
	    		+ "<INPUT TYPE=TEXT NAME=\"wedding_party_name\">"
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
	    
	    out.println("<form action=\"http://localhost:8080/sealTheDeal/registration/\">"
	    		+ "<input type=\"submit\" value=\"Return\">"
	    		+ "</form>");
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		weddingPartyName = req.getParameter("wedding_party_name");
		firstName = req.getParameter("first_name");
		lastName = req.getParameter("last_name");
		email = req.getParameter("email");
		username = req.getParameter("username");
		password = req.getParameter("password");
		passwordVerify = req.getParameter("verify_password");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		if(weddingPartyName.trim().isEmpty() || firstName.trim().isEmpty() || lastName.trim().isEmpty() || email.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || passwordVerify.trim().isEmpty()) {
			message = "ALL FIELDS MUST BE FILLED TO REGISTER";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/guest/\">");
		}
		else if(Character.isLowerCase(firstName.trim().charAt(0)) || Character.isLowerCase(lastName.trim().charAt(0))) {
			message = "THE FIRST LETTER OF YOUR FIRST AND LAST NAME MUST BE CAPITAL";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/guest/\">");
		}
		else if(!(password.equals(passwordVerify))) {
			message = "PASSWORDS MUCH MATCH";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/guest/\">");
		}
		else if(!(email.trim().contains("@"))) {
			message = "EMAIL MUST CONTAIN THE @ SYMBOL";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/guest/\">");
		}
		else if(!(email.trim().contains(".com") || email.trim().contains(".net") || email.trim().contains(".org") )) {
			message = "EMAIL MUST CONTAIN A VALID DOMAIN";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/guest/\">");
		}
		else if(userServices.getByUsername(username.trim())) {
			message = "USERNAME ALREADY EXISTS";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/guest/\">");
		}
		else if(userServices.getByEmail(email.trim())) {
			message = "EMAIL ALREADY EXISTS";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/guest/\">");
		}
		else if(!(weddingUserServices.verifyByWeddingName(weddingPartyName))) {
			message = "WEDDING PARTY DOES NOT EXISTS";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/guest/\">");
		}
		else{
			Guest newGuest = new Guest(username,firstName,lastName,password,email,2,weddingPartyName, "", "", "",false);
			guestServices.addGuest(newGuest);
			LOGGER.info("New guest added");
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/\">");
		}
		
		
	}

}
