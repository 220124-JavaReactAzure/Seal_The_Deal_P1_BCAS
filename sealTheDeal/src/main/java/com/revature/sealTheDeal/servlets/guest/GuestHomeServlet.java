package com.revature.sealTheDeal.servlets.guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.services.GuestServices;

@SuppressWarnings("serial")
public class GuestHomeServlet extends HttpServlet {

	GuestServices guestServices;
	ObjectMapper mapper;

	public GuestHomeServlet(GuestServices guestServices, ObjectMapper mapper) {
		this.guestServices = guestServices;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Guest currentGuest = guestServices.getSessionGuest();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<style>" + "body {"
				+ "background-image: url('https://ih1.redbubble.net/image.291419102.1980/st,small,507x507-pad,600x600,f8f8f8.u3.jpg');"
				+ "background-repeat: no-repeat;" + "background-attachment: fixed;" + "background-size: contain;"
				+ "background-position: center;" + "background-color: grey;" + "}" + "</style>");

		out.print("<h3>Welcome Back " + currentGuest.getFirstName() + "</h3>");

		out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/chooseMeal/\">"
				+ "<input type=\"submit\" value=\"Choose Meal\">" + "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/confirmAttendance/\">"
				+ "<input type=\"submit\" value=\"RSVP\">" + "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/viewWeddingDetails/\">"
				+ "<input type=\"submit\" value=\"View Wedding Details\">" + "</form>");
		out.println("<form action=\"http://localhost:8080/sealTheDeal/\">" + "<input type=\"submit\" value=\"Logout\">"
				+ "</form>");
	}
}
