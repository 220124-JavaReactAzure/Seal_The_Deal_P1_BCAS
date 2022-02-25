package com.revature.sealTheDeal.servlets.registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/registration/")
public class RegistrationServlet extends HttpServlet{
	
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
	    out.println("<form action=\"http://localhost:8080/sealTheDeal/registration/weddingUser/\">"
	    		+ "<input type=\"submit\" value=\"Register Wedding Party\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/registration/guest/\">"
	    		+ "<input type=\"submit\" value=\"Register Guest\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/registration/employee/\">"
	    		+ "<input type=\"submit\" value=\"Register Employee\">"
	    		+ "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/\">"
	    		+ "<input type=\"submit\" value=\"Return\">"
	    		+ "</form>");
	}
	
}
