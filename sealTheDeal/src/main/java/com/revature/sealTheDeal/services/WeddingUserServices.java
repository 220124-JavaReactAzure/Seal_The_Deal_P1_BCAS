package com.revature.sealTheDeal.services;

import org.hibernate.mapping.List;

import com.revature.sealTheDeal.dao.GuestDAO;
import com.revature.sealTheDeal.dao.WeddingUserDAO;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.models.WeddingUser;

public class WeddingUserServices {
	private final WeddingUserDAO weddingUserDAO;

	public WeddingUserServices(WeddingUserDAO weddingUserDAO) {
		this.weddingUserDAO = weddingUserDAO;
	}

	public boolean addEmployee(WeddingUser weddingUser) {
		return weddingUserDAO.addWeddingUser(weddingUser);
	}

	public List<WeddingUser> getAllWeddingUsers() {
		return weddingUserDAO.getAllWeddingUsers();
	}

	public Guest getGuestByUsername(String username) {

		return weddingUserDAO.getWeddingUserByUsername(username);
	}

	public void updateWeddingUserWithSessionMethod(WeddingUser weddingUser) {
		weddingUserDAO.updateWeddingUserWithSessionMethod(weddingUser);
	}

	public void updateWeddingUserWithHQL(WeddingUser weddingUser) {
		weddingUserDAO.updateWeddingUserWithHQL(weddingUser);
	}
}

}
