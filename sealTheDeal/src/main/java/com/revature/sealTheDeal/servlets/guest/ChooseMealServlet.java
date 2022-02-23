package com.revature.sealTheDeal.servlets.guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.Booking;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.services.GuestServices;

public class ChooseMealServlet {

	String message = null;
	Guest currentGuest;
	GuestServices guestServices;
	ObjectMapper mapper;

	public ChooseMealServlet(GuestServices guestServices, ObjectMapper mapper) {
		this.guestServices = guestServices;
		this.mapper = mapper;
	}

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			currentGuest = guestServices.getSessionGuest();
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
				if(currentGuest.getChosenMeal() != "") {
					out.println("<h3>Your Current meal chosen is "+currentGuest.getChosenMeal()+"</h3>");
					
					out.println("<HTML>"
							+ "<BODY>"
							+ "<FORM METHOD=POST>"
							+ "<P>"
							+ "Would you like to cancel this meal plan?"
							+ "</P>"
							+ "<input type=\"hidden\" name=\"cancel_meal\" value=\"true\">"
							+ "<input type=\"submit\" value=\"Cancel\">"
							+ "</form>"
							+ "</body>"
							+ "</html>");
					
					out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/\">"
							+ "<input type=\"submit\" value=\"Return\">"
							+ "</form>");
				} else { 
					mealOptions = guestServices.getByService(1, "day" + currentGuest.getDayOfWedding());
					out.println("<h3>Choose Meal</h3>");
					if(message != null) {
						out.println("<p style=\"color:red;\">"+message+"</p>");
				}
					String allOptionsInRadio = "";
				    
					for(int i=0; i < mealOptions.size(); i++) {
						Booking currentOption = mealOptions.get(i);
						if(!(currentOption.isBooked())) {
							allOptionsInRadio += "<input type=\"radio\" id=\""+ currentOption.getServiceName() +"\" name=\"meal_options\" value=\""+ i +"\">"
									+ "<label for=\""+ currentOption.getServiceName() + "\">Meal: "+currentOption.getServiceName() +"</label><br><br>";
}
					}
				}
	 }
