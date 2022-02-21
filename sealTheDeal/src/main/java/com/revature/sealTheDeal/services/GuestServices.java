package com.revature.sealTheDeal.services;


import java.util.List;

import com.revature.sealTheDeal.dao.GuestDAO;
import com.revature.sealTheDeal.models.Guest;

public class GuestServices {
	private final GuestDAO guestDAO;
	Guest sessionGuest = null;

	public GuestServices(GuestDAO guestDAO) {
		this.guestDAO = guestDAO;
	}

	public boolean addGuest(Guest guest) {
		return guestDAO.addGuest(guest);
	}

	public List<Guest> getAllGuests() {
		return (List<Guest>) guestDAO.getAllGuests();
	}

	public Guest getGuestByUsername(String username) {

		return guestDAO.getGuestByUsername(username);
	}

	public void updateGuestWithSessionMethod(Guest guest) {
		guestDAO.updateGuestWithSessionMethod(guest);
	}

	public void updateGuestWithHQL(Guest guest) {
		guestDAO.updateGuestWithHQL(guest);
	}
	
	public void setSessionGuest(Guest currentGuest) {
		sessionGuest = currentGuest;
	}
	
	public Guest getSessionGuest() {
		return sessionGuest;
	}
	
	public void closeSessionGuest() {
		sessionGuest = null;
	}
}

