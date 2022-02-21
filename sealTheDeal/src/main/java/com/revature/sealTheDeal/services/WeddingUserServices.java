package com.revature.sealTheDeal.services;


import java.util.List;

import com.revature.sealTheDeal.dao.WeddingUserDAO;
import com.revature.sealTheDeal.models.WeddingUser;

public class WeddingUserServices {
	private final WeddingUserDAO weddingUserDAO;
	WeddingUser sessionWeddingUser = null;

	public WeddingUserServices(WeddingUserDAO weddingUserDAO) {
		this.weddingUserDAO = weddingUserDAO;
	}

	public boolean addWeddingUser(WeddingUser weddingUser) {
		return weddingUserDAO.addWeddingUser(weddingUser);
	}

	public List<WeddingUser> getAllWeddingUsers() {
		return weddingUserDAO.getAllWeddingUser();
	}

	public WeddingUser getWeddingUserByUsername(String username) {

		return weddingUserDAO.getWeddingUserByUsername(username);
	}

	public void updateWeddingUserWithSessionMethod(WeddingUser weddingUser) {
		weddingUserDAO.updateWeddingUserWithSessionMethod(weddingUser);
	}

	public void updateWeddingUserWithHQL(WeddingUser weddingUser) {
		weddingUserDAO.updateWeddingUserWithHQL(weddingUser);
	}

	public boolean verifyByWeddingName(String weddingName) {
		return weddingUserDAO.verifyByWeddingName(weddingName);
	}
	
	public void setSessionWeddingUser(WeddingUser currentWeddingUser) {
		sessionWeddingUser = currentWeddingUser;
	}
	
	public WeddingUser getSessionWeddingUser() {
		return sessionWeddingUser;
	}
	
	public void closeSessionWeddingUser() {
		sessionWeddingUser = null;
	}
}


