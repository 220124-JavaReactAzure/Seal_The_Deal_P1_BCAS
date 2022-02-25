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
import com.revature.sealTheDeal.services.GuestServices;
import com.revature.sealTheDeal.servlets.employee.EmployeeHomeServlet;

public class ChooseMealServlet extends HttpServlet {

	String message = null;
	Guest currentGuest;
	GuestServices guestServices;
	ObjectMapper mapper;
	
	private static final Logger LOGGER = Logger.getLogger(ChooseMealServlet.class.getName());

	public ChooseMealServlet(GuestServices guestServices, ObjectMapper mapper) {
		this.guestServices = guestServices;
		this.mapper = mapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		currentGuest = guestServices.getSessionGuest();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<style>" + "body {"
				+ "background-image: url('https://ih1.redbubble.net/image.291419102.1980/st,small,507x507-pad,600x600,f8f8f8.u3.jpg');"
				+ "background-repeat: no-repeat;" + "background-attachment: fixed;" + "background-size: contain;"
				+ "background-position: center;" + "background-color: grey;" + "}" + "</style>");
		if(currentGuest.isAttending()) {
			if (!(currentGuest.getFoodType().equals(""))) {
				if(!(currentGuest.getPlusOne().equals(""))) {
					out.println("<h3>Your current meal chosen is " + currentGuest.getFoodType() + "</h3>");
					out.println("<h3>" + currentGuest.getPlusOne() + "'s meal chosen is " + currentGuest.getPlusOneFoodType() + "</h3>");
					
					out.println(
							"<HTML>" + "<BODY>" + "<FORM METHOD=POST>" + "<P>" + "Would you like to cancel this meal plan?"
									+ "</P>" + "<input type=\"hidden\" name=\"cancel_with_plus_one_meal\" value=\"true\">"
									+ "<input type=\"submit\" value=\"Cancel\">" + "</form>" + "</body>" + "</html>");

					out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/\">"
							+ "<input type=\"submit\" value=\"Return\">" + "</form>");
					
				}else {
					out.println("<h3>Your current meal chosen is " + currentGuest.getFoodType() + "</h3>");
					
					out.println(
							"<HTML>" + "<BODY>" + "<FORM METHOD=POST>" + "<P>" + "Would you like to cancel this meal plan?"
									+ "</P>" + "<input type=\"hidden\" name=\"cancel_meal\" value=\"true\">"
									+ "<input type=\"submit\" value=\"Cancel\">" + "</form>" + "</body>" + "</html>");

					out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/\">"
							+ "<input type=\"submit\" value=\"Return\">" + "</form>");
				}
			} else {
				if(!(currentGuest.getPlusOne().equals(""))) {
					out.println("<h3>Choose Your Meal</h3>");
					if (message != null) {
						out.println("<p style=\"color:red;\">" + message + "</p>");
					}
					out.println("<HTML>" + "<BODY>" + "<FORM METHOD=POST>"
							+ "<input type=\"radio\" id=\"steak_dinner\" name=\"food_type\" value=\"steak\">"
							+ "<label for=\"steak_dinner\">Meal Choice: Steak</label><br>"
							+ "<input type=\"radio\" id=\"salmon_dinner\" name=\"food_type\" value=\"salmon\">"
							+ "<label for=\"salmon_dinner\">Meal Choice: Salmon</label><br>"
							+ "<input type=\"radio\" id=\"greek_salad_dinner\" name=\"food_type\" value=\"greek salad\">"
							+ "<label for=\"greek_salad_dinner\">Meal Choice: Greek Salad</label><br>" + "<P>" + "</P>"
							+ "<h3>Choose "+ currentGuest.getPlusOne() +"'s Meal</h3>"
							+ "<input type=\"radio\" id=\"steak_dinner\" name=\"plus_one_food_type\" value=\"steak\">"
							+ "<label for=\"steak_dinner\">Meal Choice: Steak</label><br>"
							+ "<input type=\"radio\" id=\"salmon_dinner\" name=\"plus_one_food_type\" value=\"salmon\">"
							+ "<label for=\"salmon_dinner\">Meal Choice: Salmon</label><br>"
							+ "<input type=\"radio\" id=\"greek_salad_dinner\" name=\"plus_one_food_type\" value=\"greek salad\">"
							+ "<label for=\"greek_salad_dinner\">Meal Choice: Greek Salad</label><br>" + "<P>" + "</P>"
							+ "<input type=\"hidden\" name=\"cancel_with_plus_one_meal\" value=\"false\">"
							+ "<input type=\"submit\" value=\"Choose Meal\">" + "</form>" + "</body>" + "</html>");

					out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/\">"
							+ "<input type=\"submit\" value=\"Return\">" + "</form>");
				}else {
					out.println("<h3>Choose Your Meal</h3>");
					if (message != null) {
						out.println("<p style=\"color:red;\">" + message + "</p>");
					}
					out.println("<HTML>" + "<BODY>" + "<FORM METHOD=POST>"
							+ "<input type=\"radio\" id=\"steak_dinner\" name=\"food_type\" value=\"steak\">"
							+ "<label for=\"steak_dinner\">Meal Choice: Steak</label><br>"
							+ "<input type=\"radio\" id=\"salmon_dinner\" name=\"food_type\" value=\"salmon\">"
							+ "<label for=\"salmon_dinner\">Meal Choice: Salmon</label><br>"
							+ "<input type=\"radio\" id=\"greek_salad_dinner\" name=\"food_type\" value=\"greek salad\">"
							+ "<label for=\"greek_salad_dinner\">Meal Choice: Greek Salad</label><br>" + "<P>" + "</P>"
							+ "<input type=\"hidden\" name=\"cancel_meal\" value=\"false\">"
							+ "<input type=\"submit\" value=\"Choose Meal\">" + "</form>" + "</body>" + "</html>");

					out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/\">"
							+ "<input type=\"submit\" value=\"Return\">" + "</form>");
				}
				
			}
		} else {
			out.println("<h3>You have not confirmed your attendance to " + currentGuest.getWeddingPartyName()+ ".</h3>");
			out.println("<h3>Please confirm attendance to choose meal.</h3>");
			out.println("<form action=\"http://localhost:8080/sealTheDeal/guestHome/\">"
					+ "<input type=\"submit\" value=\"Return\">" + "</form>");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		if(!(currentGuest.getPlusOne().equals(""))) {
			boolean cancelMealWithPlusOne = Boolean.valueOf(req.getParameter("cancel_with_plus_one_meal"));
			if(cancelMealWithPlusOne) {
				currentGuest.setFoodType("");
				currentGuest.setPlusOneFoodType("");
				guestServices.updateGuestWithSessionMethod(currentGuest);
				LOGGER.info("Meal choice cancelled");
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/guestHome/\">");
			}else {
				currentGuest.setFoodType(req.getParameter("food_type"));
				currentGuest.setPlusOneFoodType(req.getParameter("plus_one_food_type"));
				guestServices.updateGuestWithSessionMethod(currentGuest);
				LOGGER.info("Meal choice updated");
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/guestHome/\">");
			}		
		}else {
			boolean cancelMeal = Boolean.valueOf(req.getParameter("cancel_meal"));
			if(cancelMeal) {
				currentGuest.setFoodType("");
				guestServices.updateGuestWithSessionMethod(currentGuest);
				LOGGER.info("Meal choice cancelled");
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/guestHome/\">");
			}else {
				currentGuest.setFoodType(req.getParameter("food_type"));
				guestServices.updateGuestWithSessionMethod(currentGuest);
				LOGGER.info("Meal choice updated");
				out.println("<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/guestHome/\">");
			}
		}
	}
}
