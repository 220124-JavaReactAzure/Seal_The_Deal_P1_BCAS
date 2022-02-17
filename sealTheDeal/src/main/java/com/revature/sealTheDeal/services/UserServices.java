package com.revature.sealTheDeal.services;

import com.revature.sealTheDeal.dao.UserDAO;

public class UserServices {
	private final UserDAO userDAO;
	
	public UserServices(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public boolean getByUsername(String username) {
		return userDAO.getByUsername(username);
	}

	public boolean getByEmail(String email) {
		return userDAO.getByEmail(email);
	}

}
