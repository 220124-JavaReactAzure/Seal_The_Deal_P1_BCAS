package com.revature.sealTheDeal.servlets.weddingUser;

import javax.servlet.http.HttpServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.sealTheDeal.services.WeddingUserServices;

public class CreateGiftRegistry extends HttpServlet {
	
	WeddingUserServices weddingUserServices; 
	ObjectMapper mapper;
	public CreateGiftRegistry(WeddingUserServices weddingUserServices, ObjectMapper mapper) {
		this.weddingUserServices = weddingUserServices;
		this.mapper = mapper;
	}

}
