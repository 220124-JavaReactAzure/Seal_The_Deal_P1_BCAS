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
