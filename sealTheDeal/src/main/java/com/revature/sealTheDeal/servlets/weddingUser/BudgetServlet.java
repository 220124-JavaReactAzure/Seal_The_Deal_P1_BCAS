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

public class BudgetServlet extends HttpServlet {
	
	String message = null;
	double budget = 0;
	
	WeddingUserServices weddingUserServices; 
	ObjectMapper mapper;
	public BudgetServlet(WeddingUserServices weddingUserServices, ObjectMapper mapper) {
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
	    
	    out.println("<h3>Change and View Wedding Budget</h3>");
	    if(message != null) {
	    	out.println("<p style=\"color:red;\">"+message+"</p>");
	    }
	    out.println("<HTML>"
	    		+ "<BODY>"
	    		+ "<FORM METHOD=POST>Current Wedding Budget: "
	    		+ "<INPUT TYPE=TEXT NAME=\"wedding_budget\" value="+currentWeddingUser.getWeddingBudget()+">"
	    		+ "<P>"
	    		+ "Current Wedding Price: " + currentWeddingUser.getWeddingCost()
	    		+ "</P>"
	    		+ "<INPUT TYPE=SUBMIT>"
	    		+ "</FORM>"
	    		+ "</BODY>"
	    		+ "</HTML>");
	    
	    out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/\">"
	    		+ "<input type=\"submit\" value=\"Return\">"
	    		+ "</form>");
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//budget = Double.valueOf(req.getParameter("wedding_budget"));
		WeddingUser currentWeddingUser = weddingUserServices.getSessionWeddingUser();

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		if (req.getParameter("wedding_budget").trim().isEmpty()) {
			message = "ALL FIELDS MUST BE FILLED TO CHANGE BUDGET";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/budget/\">");
		}
		else if(weddingUserServices.CheckNumeric(req.getParameter("wedding_budget").trim())) {
			message = "WEDDING BUDGET MUST BE NUMERIC ONLY";
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/budget/\">");
		}
		else{
			budget = Double.valueOf(req.getParameter("wedding_budget"));
			currentWeddingUser.setWeddingBudget(budget);
			weddingUserServices.updateWeddingUserWithSessionMethod(currentWeddingUser);
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/\">");
		}
	}
}
