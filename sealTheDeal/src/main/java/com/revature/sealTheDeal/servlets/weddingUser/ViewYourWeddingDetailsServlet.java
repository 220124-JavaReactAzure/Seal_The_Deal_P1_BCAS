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

public class ViewYourWeddingDetailsServlet extends HttpServlet {
	WeddingUserServices weddingUserServices;
	ObjectMapper mapper;
	WeddingUser currentWeddingUser;
	
	public ViewYourWeddingDetailsServlet(WeddingUserServices weddingUserServices, ObjectMapper mapper) {
		this.weddingUserServices = weddingUserServices;
		this.mapper = mapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		currentWeddingUser = weddingUserServices.getSessionWeddingUser();
		String florist = "no florist currently booked";
		String caterer = "no caterer currently booked";
		String photographer = "no photographer currently booked";
		String musician = "no caterer currently booked";
		String venue = "no venue currently booked...WHERE IS THE WEDDING?!";
		String dayOfWedding = "";
		String temp = Integer.toString(currentWeddingUser.getDayOfWedding());
		if(currentWeddingUser.getDayOfWedding()>10000000) {
			dayOfWedding += temp.charAt(0) + "" + temp.charAt(1);
			dayOfWedding += "/";
			dayOfWedding += temp.charAt(2) + "" + temp.charAt(3);
			dayOfWedding += "/";
			dayOfWedding += temp.charAt(4) + "" + temp.charAt(5) + "" + temp.charAt(6) + "" + temp.charAt(7);
		}else {
			dayOfWedding += "0" + "" + temp.charAt(0);
			dayOfWedding += "/";
			dayOfWedding += temp.charAt(1) + "" + temp.charAt(2);
			dayOfWedding += "/";
			dayOfWedding += temp.charAt(3) + "" + temp.charAt(4) + "" + temp.charAt(5) + "" + temp.charAt(6);
		}

		if (!(currentWeddingUser.getBookedFlorist().equals(""))) {
			florist = currentWeddingUser.getBookedFlorist();
		}
		if (!(currentWeddingUser.getBookedCaterer().equals(""))) {
			caterer = currentWeddingUser.getBookedCaterer();
		}
		if (!(currentWeddingUser.getBookedPhotographer().equals(""))) {
			photographer = currentWeddingUser.getBookedPhotographer();
		}
		if (!(currentWeddingUser.getBookedMusician().equals(""))) {
			musician = currentWeddingUser.getBookedMusician();
		}
		if (!(currentWeddingUser.getBookedVenue().equals(""))) {
			venue = currentWeddingUser.getBookedVenue();
		}

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<style>" + "body {"
				+ "background-image: url('https://ih1.redbubble.net/image.291419102.1980/st,small,507x507-pad,600x600,f8f8f8.u3.jpg');"
				+ "background-repeat: no-repeat;" + "background-attachment: fixed;" + "background-size: contain;"
				+ "background-position: center;" + "background-color: grey;" + "}" + "</style>");

		out.print("<h3>Hello, " + currentWeddingUser.getFirstName() + "</h3>");
		out.println("<h3> Details for the " + currentWeddingUser.getWeddingPartyName() + " wedding</h3>");
		out.println("<h3> The " + currentWeddingUser.getPetTypeGroom() + " groom: " + currentWeddingUser.getPetNameGroom() + "</h3>");
		out.println("<h3> The " + currentWeddingUser.getPetTypeBride() + " bride: " + currentWeddingUser.getPetNameBride() +"</h3>");
		out.println("<h3> The day of the wedding is: " + dayOfWedding + "</h3>");
		out.println("<h3> Current number of confimed guest: " + currentWeddingUser.getNumberOfGuests() + "</h3>");
		out.println("<h3> The venue of the wedding is: " + venue + "</h3>");
		out.println("<h3> The caterer for the wedding will be: " + caterer + "</h3>");
		out.println("<h3> The florist for the wedding will be: " + florist + "</h3>");
		out.println("<h3> With music by: " + musician + "</h3>");
		out.println("<h3> Professional photography by: " + photographer + "</h3>");
		
		out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/\">"
				+ "<input type=\"submit\" value=\"Return\">" + "</form>");
	
	}
}
