package com.revature.sealTheDeal.servlets.guest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.models.WeddingUser;
import com.revature.sealTheDeal.services.GuestServices;
import com.revature.sealTheDeal.services.WeddingUserServices;
import com.revature.sealTheDeal.servlets.employee.AddCaterersServlet;

public class ConfirmAttendanceServlet extends HttpServlet {

	String message = null;
	Guest currentGuest;
	GuestServices guestServices;
	ObjectMapper mapper;
	WeddingUserServices weddingUserServices;
	WeddingUser weddingUser;
	
	private static final Logger LOGGER = Logger.getLogger(ConfirmAttendanceServlet.class.getName());

	public ConfirmAttendanceServlet(GuestServices guestServices, WeddingUserServices weddingUserServices,
			ObjectMapper mapper) {
		this.guestServices = guestServices;
		this.weddingUserServices = weddingUserServices;
		this.mapper = mapper;
	}

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

		if (currentGuest.isAttending()) {
			out.println("<h3>Cancel attendance to the " + currentGuest.getWeddingPartyName() + " wedding?</h3>");

			out.println("<HTML>" + "<BODY>" + "<FORM METHOD=POST>" + "<P>" + "Would you like to cancel your RSVP?"
					+ "</P>" + "<input type=\"hidden\" name=\"cancel_attendance\" value=\"true\">"
					+ "<input type=\"submit\" value=\"Cancel\">" + "</form>" + "</body>" + "</html>");

			out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/\">"
					+ "<input type=\"submit\" value=\"Return\">" + "</form>");
		} else {

			out.println("<h3>Confirm Attendance</h3>");
			if (message != null) {
				out.println("<p style=\"color:red;\">" + message + "</p>");

			}
			out.println("<HTML>" + "<BODY>" + "<FORM METHOD=POST>"
					+ "<input type=\"radio\" id=\"is_attending\" name=\"confirm_attendance\" value=\"true\">"
					+ "<label for=\"is_attending\">Check for attendance confirmation</label><br>"
					+ "<input type=\"radio\" id=\"is_not_attending\" name=\"confirm_attendance\" value=\"false\">"
					+ "<label for=\"is_not_attending\">Check if not attending</label><br>"
					+ "<input type=\"checkbox\" id=\"plus_one_box\" value=\"1\" name=\"plus_one\">"
					+ "Would you like to bring a Plus One?<br>"
					+ "<label for=\"plus_one_name\">Enter name of your Plus One: </label>"
					+ "<input type=\"text\" id=\"plus_one_name\" name=\"plus_one_name\"><br>" + "</P>"
					+ "<input type=\"hidden\" name=\"cancel_attendance\" value=\"false\">"
					+ "<input type=\"submit\" value=\"Submit\">" + "</form>" + "</body>" + "</html>");

			out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/\">"
					+ "<input type=\"submit\" value=\"Return\">" + "</form>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		boolean cancelAttendance = Boolean.valueOf(req.getParameter("cancel_attendance"));

		if (cancelAttendance) {

			currentGuest.setAttendance(false);
			if (currentGuest.getPlusOne().equals("")) {
				weddingUser.setNumberOfGuests(weddingUser.getNumberOfGuests() - 1);

			} else {
				weddingUser.setNumberOfGuests(weddingUser.getNumberOfGuests() - 2);
				currentGuest.setPlusOne("");
			}
			weddingUserServices.updateWeddingUserWithSessionMethod(weddingUser);
			guestServices.updateGuestWithSessionMethod(currentGuest);
			LOGGER.info("Updated wedding attendance");
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/guestHome/\">");
		} else {
			boolean confirmAttendance = Boolean.valueOf(req.getParameter("confirm_attendance"));
			int plusOne = 0;
			try {
				if(req.getParameter("plus_one").equals("1")) {
				plusOne += Integer.valueOf(req.getParameter("plus_one"));
				}
			} catch (NullPointerException e){
			}
			
			if (confirmAttendance) {
				currentGuest.setAttendance(true);
				if (plusOne == 1) {
					currentGuest.setPlusOne(req.getParameter("plus_one_name"));
					weddingUser.setNumberOfGuests(weddingUser.getNumberOfGuests() + 2);
				} else {
					weddingUser.setNumberOfGuests(weddingUser.getNumberOfGuests() + 1);
				}
				weddingUserServices.updateWeddingUserWithSessionMethod(weddingUser);
				guestServices.updateGuestWithSessionMethod(currentGuest);
				LOGGER.info("Plus One attendance updated");
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/guestHome/\">");
			}else {
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/guestHome/\">");
			}
		}

	}
}
