package com.revature.sealTheDeal.servlets.guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.models.WeddingUser;
import com.revature.sealTheDeal.services.GuestServices;
import com.revature.sealTheDeal.services.WeddingUserServices;

public class ViewWeddingDetailsServlet extends HttpServlet {
	
	
	String message = null;
	Guest currentGuest;
	GuestServices guestServices;
	ObjectMapper mapper;
	WeddingUserServices weddingUserServices;
	WeddingUser weddingUser;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		currentGuest = guestServices.getSessionGuest();
		weddingUser = weddingUserServices.getByWeddingName(currentGuest.getWeddingPartyName());
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<style>" + "body {"
				+ "background-image: url('https://ih1.redbubble.net/image.291419102.1980/st,small,507x507-pad,600x600,f8f8f8.u3.jpg');"
				+ "background-repeat: no-repeat;" + "background-attachment: fixed;" + "background-size: contain;"
				+ "background-position: center;" + "background-color: grey;" + "}" + "</style>");
		
		out.print("<h3>Hello, " + currentGuest.getFirstName() + "</h3>");
		out.println("<h3> Details for the" + currentGuest.getWeddingPartyName() + " wedding</h3>");
		if(currentGuest.getPlusOne().equals("")) {
			out.println("<h3>You do not currently have a Plus One registered</h3>");
		} else { out.println("<h3> Your Plus One," + currentGuest.getPlusOne(req.getParameter("plus_one_name")) + "has ordered" + );
		}
		
		}
		out.println("<h3> You have ordered" + currentGuest.getFoodType() + "for dinner"</h3>);
}
	
	
}
