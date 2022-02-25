package com.revature.sealTheDeal.servlets.weddingUser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.WeddingUser;
import com.revature.sealTheDeal.services.WeddingUserServices;

@SuppressWarnings("serial")
public class WeddingUserHomeServlet extends HttpServlet {
	
	WeddingUserServices weddingUserServices; 
	ObjectMapper mapper;
	public WeddingUserHomeServlet(WeddingUserServices weddingUserServices, ObjectMapper mapper) {
		this.weddingUserServices = weddingUserServices;
		this.mapper = mapper;
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WeddingUser currentWeddingUser = weddingUserServices.getSessionWeddingUser();
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
	    
	    out.print("<h3>Welcome Back " + currentWeddingUser.getFirstName() + "</h3>");
	    
	    
	    out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/viewYourWeddingDetails/\">"
	    		+ "<input type=\"submit\" value=\"View Wedding Details\">"
	    		+ "</form>");
	    out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/budget/\">"
	    		+ "<input type=\"submit\" value=\"Set Budget\">"
	    		+ "</form>");
	    out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/chooseCaterers/\">"
	    		+ "<input type=\"submit\" value=\"Choose Caterer\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/chooseFlorists/\">"
	    		+ "<input type=\"submit\" value=\"Choose Florist\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/chooseMusicians/\">"
	    		+ "<input type=\"submit\" value=\"Choose Musician\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/choosePhotographers/\">"
	    		+ "<input type=\"submit\" value=\"Choose Photographer\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/chooseVenues/\">"
	    		+ "<input type=\"submit\" value=\"Choose Venue\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/\">"
	    		+ "<input type=\"submit\" value=\"Logout\">"
	    		+ "</form>");
	}

}
