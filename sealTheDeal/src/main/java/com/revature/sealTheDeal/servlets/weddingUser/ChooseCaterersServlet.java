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

public class ChooseCaterersServlet extends HttpServlet  {
	
	String message = null;
	WeddingUser currentWeddingUser = null;
	List<Booking> bookingOptions = null;
	Boolean cancelBooking;
	
	WeddingUserServices weddingUserServices;
	EmployeeServices employeeServices;
	ObjectMapper mapper;
	
	
	public ChooseCaterersServlet(EmployeeServices employeeServices, WeddingUserServices weddingUserServices, ObjectMapper mapper) {
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
		if(!(currentWeddingUser.getBookedCaterer().equals(""))) {
			out.println("<h3>Your Current Caterer is "+currentWeddingUser.getBookedCaterer()+"</h3>");
			
			out.println("<HTML>"
					+ "<BODY>"
					+ "<FORM METHOD=POST>"
					+ "<P>"
					+ "Would you like to cancel your caterer booking?"
					+ "</P>"
					+ "<input type=\"hidden\" name=\"cancel_booking\" value=\"true\">"
					+ "<input type=\"submit\" value=\"Cancel Caterer Booking\">"
					+ "</form>"
					+ "</body>"
					+ "</html>");
			
			out.println("<form action=\"http://localhost:8080/sealTheDeal/weddingUserHome/\">"
					+ "<input type=\"submit\" value=\"Return\">"
					+ "</form>");
		}else {
			bookingOptions = employeeServices.getByService(1, "day" + currentWeddingUser.getDayOfWedding());
	    
			out.println("<h3>Choose Caterer for the Wedding</h3>");
			if(message != null) {
				out.println("<p style=\"color:red;\">"+message+"</p>");
			}
	    
			String allOptionsInRadio = "";
	    
			for(int i=0; i < bookingOptions.size(); i++) {
				Booking currentOption = bookingOptions.get(i);
				if(!(currentOption.isBooked())) {
					allOptionsInRadio += "<input type=\"radio\" id=\""+ currentOption.getServiceName() +"\" name=\"caterer_options\" value=\""+ i +"\">"
							+ "<label for=\""+ currentOption.getServiceName() + "\">Caterer: "+currentOption.getServiceName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Price: $" +currentOption.getPrice() +"</label><br><br>";
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
					+ "<input type=\"submit\" value=\"Book Caterer\">"
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
			Booking unbookCaterer = employeeServices.getBookedService(currentWeddingUser.getBookedCaterer(), "day"+currentWeddingUser.getDayOfWedding());
			unbookCaterer.setBooked(false);
			currentWeddingUser.setBookedCaterer("");
			currentWeddingUser.setWeddingCost(currentWeddingUser.getWeddingCost() - unbookCaterer.getPrice());
			weddingUserServices.updateWeddingUserWithSessionMethod(currentWeddingUser);
			employeeServices.updateBooking(unbookCaterer, "day"+currentWeddingUser.getDayOfWedding());
			out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/\">");
		} else {
			Booking bookThisCaterer = bookingOptions.get(Integer.valueOf(req.getParameter("caterer_options")));
			if(currentWeddingUser.getWeddingBudget()<bookThisCaterer.getPrice()) {
				message = "YOU CANNOT BOOK A CATERER WITH A HIGHER PRICE THAN YOUR REMAINING BUDGET";
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/chooseCaterers/\">");
			} else {
				currentWeddingUser.setBookedCaterer(bookThisCaterer.getServiceName());
				currentWeddingUser.setWeddingCost(currentWeddingUser.getWeddingCost() + bookThisCaterer.getPrice());
				bookThisCaterer.setBooked(true);
				weddingUserServices.updateWeddingUserWithSessionMethod(currentWeddingUser);
				employeeServices.updateBooking(bookThisCaterer, "day"+currentWeddingUser.getDayOfWedding());
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/weddingUserHome/\">");
			}
		}
	}

}
