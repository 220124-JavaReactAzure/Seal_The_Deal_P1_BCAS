package com.revature.sealTheDeal.servlets.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.WeddingUser;
import com.revature.sealTheDeal.services.EmployeeServices;
import com.revature.sealTheDeal.services.UserServices;
import com.revature.sealTheDeal.services.WeddingUserServices;


@SuppressWarnings("serial")
public class RegisterWeddingUserServlet extends HttpServlet{
	
	String weddingName = null;
	String groomSpecies = null;
	String groomName = null;
	String brideSpecies = null;
	String brideName = null;
	int date = 0;
	String firstName = null;
	String lastName = null;
	String email = null;
	String username = null;
	String password = null;
	String passwordVerify = null;
	String message = null;
	
	UserServices userServices;
	WeddingUserServices weddingUserServices;
	EmployeeServices employeeServices;
	ObjectMapper mapper;
	
	private static final Logger LOGGER = Logger.getLogger(RegisterWeddingUserServlet.class.getName());
	
	public RegisterWeddingUserServlet(UserServices userServices, WeddingUserServices weddingUserServices,EmployeeServices employeeServices,
			ObjectMapper mapper) {
		this.userServices = userServices;
		this.weddingUserServices = weddingUserServices;
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
	    
	    out.println("<h3>Register New Wedding</h3>");
	    if(message != null) {
	    	out.println("<p style=\"color:red;\">"+message+"</p>");
	    }
	    out.println("<HTML>"
	    		+ "<BODY>"
	    		+ "<FORM METHOD=POST>Wedding Party Name: "
	    		+ "<INPUT TYPE=TEXT NAME=\"wedding_name\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Pet Groom Species: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"pet_groom_species\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Pet Groom Name: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"pet_groom_name\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Pet Bride Species: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"pet_bride_species\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Pet Bride Name: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"pet_bride_name\">"
	    		+ "<P>"
	    		+ "<label for=\"month\">Wedding Date:</label>"
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<select name=\"month\" id=\"month\">"
	    		+ "<option value=\"01\">January</option>"
	    		+ "<option value=\"02\">February</option>"
	    		+ "<option value=\"03\">March</option>"
	    		+ "<option value=\"04\">April</option>"
	    		+ "<option value=\"05\">May</option>"
	    		+ "<option value=\"06\">June</option>"
	    		+ "<option value=\"07\">July</option>"
	    		+ "<option value=\"08\">August</option>"
	    		+ "<option value=\"09\">September</option>"
	    		+ "<option value=\"10\">October</option>"
	    		+ "<option value=\"11\">November</option>"
	    		+ "<option value=\"12\">December</option>"
	    		+ "</select>"
	    		+ "<select name=\"day\" id=\"day\">"
	    		+ "<option value=\"01\">1</option>"
	    		+ "<option value=\"02\">2</option>"
	    		+ "<option value=\"03\">3</option>"
	    		+ "<option value=\"04\">4</option>"
	    		+ "<option value=\"05\">5</option>"
	    		+ "<option value=\"06\">6</option>"
	    		+ "<option value=\"07\">7</option>"
	    		+ "<option value=\"08\">8</option>"
	    		+ "<option value=\"09\">9</option>"
	    		+ "<option value=\"10\">10</option>"
	    		+ "<option value=\"11\">11</option>"
	    		+ "<option value=\"12\">12</option>"
	    		+ "<option value=\"13\">13</option>"
	    		+ "<option value=\"14\">14</option>"
	    		+ "<option value=\"15\">15</option>"
	    		+ "<option value=\"16\">16</option>"
	    		+ "<option value=\"17\">17</option>"
	    		+ "<option value=\"18\">18</option>"
	    		+ "<option value=\"19\">19</option>"
	    		+ "<option value=\"20\">20</option>"
	    		+ "<option value=\"21\">21</option>"
	    		+ "<option value=\"22\">22</option>"
	    		+ "<option value=\"23\">23</option>"
	    		+ "<option value=\"24\">24</option>"
	    		+ "<option value=\"25\">25</option>"
	    		+ "<option value=\"26\">26</option>"
	    		+ "<option value=\"27\">27</option>"
	    		+ "<option value=\"28\">28</option>"
	    		+ "<option value=\"29\">29</option>"
	    		+ "<option value=\"30\">30</option>"
	    		+ "<option value=\"31\">31</option>"
	    		+ "</select>"
	    		+ "<select name=\"year\" id=\"year\">"
	    		+ "<option value=\"2022\">2022</option>"
	    		+ "<option value=\"2023\">2023</option>"
	    		+ "<option value=\"2024\">2024</option>"
	    		+ "<option value=\"2025\">2025</option>"
	    		+ "</select>"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Your First Name: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
	    		+ "<INPUT TYPE=TEXT NAME=\"first_name\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=POST>Your Last Name: "
	    		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //blank space for spacing on website
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
		
		
		weddingName = req.getParameter("wedding_name");
		groomSpecies = req.getParameter("pet_groom_species");
		groomName = req.getParameter("pet_groom_name");
		brideSpecies = req.getParameter("pet_bride_species");
		brideName = req.getParameter("pet_bride_name");
		date = Integer.valueOf(req.getParameter("month") + req.getParameter("day") + req.getParameter("year"));
		firstName = req.getParameter("first_name");
		lastName = req.getParameter("last_name");
		email = req.getParameter("email");
		username = req.getParameter("username");
		password = req.getParameter("password");
		passwordVerify = req.getParameter("verify_password");
		
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		if(weddingName.trim().isEmpty() || groomSpecies.trim().isEmpty() || groomName.trim().isEmpty() || brideSpecies.trim().isEmpty() || brideName.trim().isEmpty() || firstName.trim().isEmpty() || lastName.trim().isEmpty() || email.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || passwordVerify.trim().isEmpty()) {
			message = "ALL FIELDS MUST BE FILLED TO REGISTER";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/weddingUser/\">");
		}
		else if(Character.isLowerCase(firstName.trim().charAt(0)) || Character.isLowerCase(lastName.trim().charAt(0))) {
			message = "THE FIRST LETTER OF YOUR FIRST AND LAST NAME MUST BE CAPITAL";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/weddingUser/\">");
		}
		else if(!(password.equals(passwordVerify))) {
			message = "PASSWORDS MUCH MATCH";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/weddingUser/\">");
		}
		else if(!(email.trim().contains("@"))) {
			message = "EMAIL MUST CONTAIN THE @ SYMBOL";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/weddingUser/\">");
		}
		else if(!(email.trim().contains(".com") || email.trim().contains(".net") || email.trim().contains(".org") )) {
			message = "EMAIL MUST CONTAIN A VALID DOMAIN";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/weddingUser/\">");
		}
		else if(userServices.getByUsername(username.trim())) {
			message = "USERNAME ALREADY EXISTS";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/weddingUser/\">");
		}
		else if(userServices.getByEmail(email)) {
			message = "EMAIL ALREADY EXISTS";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/weddingUser/\">");
		}
		else if(weddingUserServices.verifyByWeddingName(weddingName)) {
			message = "WEDDING NAME IS TAKEN";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/weddingUser/\">");
		}
		else{
			WeddingUser newWedding = new WeddingUser(username,firstName,lastName,password,email,3,weddingName,groomSpecies,groomName,brideSpecies,brideName,date,0,0,"","","","","",0);
			employeeServices.addWeddingDay("day"+date);
			LOGGER.info("New wedding date added");
			weddingUserServices.addWeddingUser(newWedding);
			LOGGER.info("New wedding user added");
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/\">");
		}
		
	}

}
