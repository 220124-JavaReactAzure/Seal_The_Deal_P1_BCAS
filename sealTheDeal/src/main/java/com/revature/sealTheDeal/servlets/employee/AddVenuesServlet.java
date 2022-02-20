package com.revature.sealTheDeal.servlets.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.models.Booking;
import com.revature.sealTheDeal.services.EmployeeServices;

public class AddVenuesServlet extends HttpServlet {

	EmployeeServices employeeServices;
	ObjectMapper mapper;
	String serviceName = null;
	String message = null;
	double price = 0;

	public AddVenuesServlet(EmployeeServices employeeServices, ObjectMapper mapper) {
		this.employeeServices = employeeServices;
		this.mapper = mapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<style>" + "body {"
				+ "background-image: url('https://ih1.redbubble.net/image.291419102.1980/st,small,507x507-pad,600x600,f8f8f8.u3.jpg');"
				+ "background-repeat: no-repeat;" + "background-attachment: fixed;" + "background-size: contain;"
				+ "background-position: center;" + "background-color: grey;" + "}" + "</style>");

		out.println("<h3>Add Venue Info</h3>");
		if (message != null) {
			out.println("<p style=\"color:red;\">" + message + "</p>");
		}
		out.println("<HTML>" + "<BODY>" + "<FORM METHOD=POST>Service Name: " 
				+ "&nbsp;&nbsp;&nbsp;&nbsp;"// blank space for spacing on website
				+ "<INPUT TYPE=TEXT NAME=\"service_name\">"
				+ "<P>" + "<FORM METHOD=POST>Price of Services: "
				+ "<INPUT TYPE=TEXT NAME=\"price\">" + "<P>" + "<INPUT TYPE=SUBMIT>" + "</FORM>" + "</BODY>"
				+ "</HTML>");
		
		out.println("<form action=\"http://localhost:8080/sealTheDeal/employeeHome/\">"
	    		+ "<input type=\"submit\" value=\"Return\">"
	    		+ "</form>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		serviceName = req.getParameter("service_name");
		price = Double.valueOf(req.getParameter("price"));

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		if (serviceName.trim().isEmpty() || req.getParameter("price").trim().isEmpty()) {
			message = "ALL FIELDS MUST BE FILLED TO REGISTER";
			out.println(
					"<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/employeeHome/addVenues/\">");

		}

		else if (employeeServices.getByServiceName(serviceName)) {
			message = "SERVICE NAME ALREADY EXISTS";
			out.println(
					"<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/employeeHome/addVenues/\">");
		}

		else {

			Booking newBooking = new Booking(serviceName, 5, price, false);

			employeeServices.addBooking(newBooking);
			out.println(
					"<meta http-equiv=\"refresh\" content=\"0; URL=http://localhost:8080/sealTheDeal/employeeHome/\">");

		}

	}
}
