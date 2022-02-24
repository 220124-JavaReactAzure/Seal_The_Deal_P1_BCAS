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

	public ViewWeddingDetailsServlet(GuestServices guestServices, WeddingUserServices weddingUserServices,
			ObjectMapper mapper) {
		this.guestServices = guestServices;
		this.weddingUserServices = weddingUserServices;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		currentGuest = guestServices.getSessionGuest();
		weddingUser = weddingUserServices.getByWeddingName(currentGuest.getWeddingPartyName());
		String florist = "no florist currently booked";
		String caterer = "no caterer currently booked";
		String photographer = "no photographer currently booked";
		String musician = "no caterer currently booked";
		String venue = "no venue currently booked...WHERE IS THE WEDDING?!";
		String dayOfWedding = "";
		String temp = Integer.toString(weddingUser.getDayOfWedding());
		if(weddingUser.getDayOfWedding()>10000000) {
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

		if (!(weddingUser.getBookedFlorist().equals(""))) {
			florist = weddingUser.getBookedFlorist();
		}
		if (!(weddingUser.getBookedCaterer().equals(""))) {
			caterer = weddingUser.getBookedCaterer();
		}
		if (!(weddingUser.getBookedPhotographer().equals(""))) {
			photographer = weddingUser.getBookedPhotographer();
		}
		if (!(weddingUser.getBookedMusician().equals(""))) {
			musician = weddingUser.getBookedMusician();
		}
		if (!(weddingUser.getBookedVenue().equals(""))) {
			venue = weddingUser.getBookedVenue();
		}

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<style>" + "body {"
				+ "background-image: url('https://ih1.redbubble.net/image.291419102.1980/st,small,507x507-pad,600x600,f8f8f8.u3.jpg');"
				+ "background-repeat: no-repeat;" + "background-attachment: fixed;" + "background-size: contain;"
				+ "background-position: center;" + "background-color: grey;" + "}" + "</style>");

		out.print("<h3>Hello, " + currentGuest.getFirstName() + "</h3>");
		out.println("<h3> Details for the " + currentGuest.getWeddingPartyName() + " wedding</h3>");
		out.println("<h3> The day of the wedding is: " + dayOfWedding + "</h3>");
		out.println("<h3> The venue of the wedding is: " + venue + "</h3>");
		out.println("<h3> The caterer for the wedding will be: " + caterer + "</h3>");
		if(currentGuest.getFoodType().equals("")) {
			out.println("<h3> You have not ordered anything for dinner yet </h3>");
		}else {
			out.println("<h3> You have ordered " + currentGuest.getFoodType() + " for dinner</h3>");
		}
		
		if(currentGuest.getFoodType().equals("")) {
			if (currentGuest.getPlusOne().equals("")) {
				out.println("<h3>You do not currently have a plus one registered</h3>");
			}else {
				out.println("<h3> Your plus one is " + currentGuest.getPlusOne());
			}
		}else {
			if (currentGuest.getPlusOne().equals("")) {
				out.println("<h3>You do not currently have a plus one registered</h3>");
			} else {
				out.println("<h3> Your Plus One, " + currentGuest.getPlusOne() + " has ordered "
						+ currentGuest.getPlusOneFoodType() + "</h3>");
			}
		}
		

		out.println("<h3> The florist for the wedding will be: " + florist + "</h3>");
		out.println("<h3> With music by: " + musician + "</h3>");
		out.println("<h3> Professional photography by: " + photographer + "</h3>");
		
		out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/\">"
				+ "<input type=\"submit\" value=\"Return\">" + "</form>");

	}

}
