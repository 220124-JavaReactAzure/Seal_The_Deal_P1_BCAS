package com.revature.sealTheDeal.servlets.weddingUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.Booking;
import com.revature.sealTheDeal.models.WeddingUser;
import com.revature.sealTheDeal.services.EmployeeServices;
import com.revature.sealTheDeal.services.WeddingUserServices;

@SuppressWarnings("serial")
public class ChooseVenuesServlet extends HttpServlet {
	
	String message = null;
	WeddingUser currentWeddingUser = null;
	List<Booking> bookingOptions = null;
	Boolean cancelBooking;
	
	WeddingUserServices weddingUserServices;
	EmployeeServices employeeServices;
	ObjectMapper mapper;
	
	public ChooseVenuesServlet(EmployeeServices employeeServices, WeddingUserServices weddingUserServices, ObjectMapper mapper) {
		this.employeeServices = employeeServices;
		this.weddingUserServices = weddingUserServices;
		this.mapper = mapper;
	}
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		currentWeddingUser = weddingUserServices.getSessionWeddingUser();
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
		if(!(currentWeddingUser.getBookedVenue().equals(""))) {
			out.println("<h3>Your Current Venue is "+currentWeddingUser.getBookedVenue()+"</h3>");
			
			out.println("<HTML>"
					+ "<BODY>"
					+ "<FORM METHOD=POST>"
					+ "<P>"
					+ "Would you like to cancel your venue booking?"
					+ "</P>"
					+ "<input type=\"hidden\" name=\"cancel_booking\" value=\"true\">"
					+ "<input type=\"submit\" value=\"Cancel Venue Booking\">"
					+ "</form>"
					+ "</body>"
					+ "</html>");
			
			out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/\">"
					+ "<input type=\"submit\" value=\"Return\">"
					+ "</form>");
		}else {
			bookingOptions = employeeServices.getByService(5, "day" + currentWeddingUser.getDayOfWedding());
	    
			out.println("<h3>Choose Venue for the Wedding</h3>");
			if(message != null) {
				out.println("<p style=\"color:red;\">"+message+"</p>");
			}
	    
			String allOptionsInRadio = "";
	    
			for(int i=0; i < bookingOptions.size(); i++) {
				Booking currentOption = bookingOptions.get(i);
				if(!(currentOption.isBooked())) {
					allOptionsInRadio += "<input type=\"radio\" id=\""+ currentOption.getServiceName() +"\" name=\"venue_options\" value=\""+ i +"\">"
							+ "<label for=\""+ currentOption.getServiceName() + "\">Venue: "+currentOption.getServiceName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Price: $" +currentOption.getPrice() +"</label><br><br>";
				}
			}
	    
			out.println("<HTML>"
					+ "<BODY>"
					+ "<P>"
					+ "Remaining Budget: $" + (currentWeddingUser.getWeddingBudget() - currentWeddingUser.getWeddingCost())
					+ "</P>"
					+ "<FORM METHOD=POST>"
					+ allOptionsInRadio
					+ "<P>"
					+ "<input type=\"submit\" value=\"Book Venue\">"
					+ "<input type=\"hidden\" name=\"cancel_booking\" value=\"false\">"
					+ "</form>"
					+ "</body>"
					+ "</html>");
	    
			out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/\">"
					+ "<input type=\"submit\" value=\"Return\">"
					+ "</form>");
		}
		
		
		
	    
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		cancelBooking = Boolean.valueOf(req.getParameter("cancel_booking"));
		
		if(cancelBooking) {
			Booking unbookVenue = employeeServices.getBookedService(currentWeddingUser.getBookedVenue(), "day"+currentWeddingUser.getDayOfWedding());
			unbookVenue.setBooked(false);
			currentWeddingUser.setBookedVenue("");
			currentWeddingUser.setWeddingCost(currentWeddingUser.getWeddingCost() - unbookVenue.getPrice());
			weddingUserServices.updateWeddingUserWithSessionMethod(currentWeddingUser);
			employeeServices.updateBooking(unbookVenue, "day"+currentWeddingUser.getDayOfWedding());
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/\">");
		} else {
			Booking bookThisVenue = bookingOptions.get(Integer.valueOf(req.getParameter("venue_options")));
			if(currentWeddingUser.getWeddingBudget()<bookThisVenue.getPrice()) {
				message = "YOU CANNOT BOOK A VENUE WITH A HIGHER PRICE THAN YOUR REMAINING BUDGET";
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/chooseVenues/\">");
			} else {
				currentWeddingUser.setBookedVenue(bookThisVenue.getServiceName());
				currentWeddingUser.setWeddingCost(currentWeddingUser.getWeddingCost() + bookThisVenue.getPrice());
				bookThisVenue.setBooked(true);
				weddingUserServices.updateWeddingUserWithSessionMethod(currentWeddingUser);
				employeeServices.updateBooking(bookThisVenue, "day"+currentWeddingUser.getDayOfWedding());
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/\">");
			}
		}
	}

}
