package com.revature.sealTheDeal.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
		
		
		resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();
	    out.println("<HTML>"
	    		+ "<BODY>"
	    		+ "<FORM METHOD=GET>Username: "
	    		+ "<INPUT TYPE=TEXT NAME=\"username\">"
	    		+ "<P>"
	    		+ "<FORM METHOD=GET>Password: "
	    		+ "<INPUT TYPE=PASSWORD NAME=\"password\">"
	    		+ "<P>"
	    		+ "<INPUT TYPE=SUBMIT>"
	    		+ "</FORM>"
	    		+ "</BODY>"
	    		+ "</HTML>");
	    
	    

	    String name = req.getParameter("username");
	    out.println("<HTML>");
	    out.println("<HEAD><TITLE>Hello, " + name + "</TITLE></HEAD>");
	    out.println("<BODY>");
	    out.println("Hello, " + name);
	    out.println("</BODY></HTML>");
	    
	    out.println("<form action=\"http://localhost:8080/sealTheDeal/registration/\">"
	    		+ "<input type=\"submit\" value=\"Registration\">"
	    		+ "</form>");
	}
	
}
