package com.revature.sealTheDeal.servlets.registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.services.UserServices;
import com.revature.sealTheDeal.services.WeddingUserServices;


public class RegisterWeddingUserServlet extends HttpServlet{
	
	String weddingName = null;
	String groomSpecies = null;
	String groomName = null;
	String brideSpecies = null;
	String brideName = null;
	String firstName = null;
	String lastName = null;
	String email = null;
	String username = null;
	String password = null;
	String passwordVerify = null;
	String message = null;
	
	UserServices userServices;
	WeddingUserServices weddingUserServices;
	ObjectMapper mapper;
	
	
	public RegisterWeddingUserServlet(UserServices userServices, WeddingUserServices weddingUserServices,
			ObjectMapper mapper) {
		this.userServices = userServices;
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
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		message = "day" + req.getParameter("month") + req.getParameter("day") + req.getParameter("year");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/registration/weddingUser/\">");
	}

}
