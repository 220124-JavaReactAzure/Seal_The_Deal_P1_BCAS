package com.revature.sealTheDeal.services;

import org.hibernate.mapping.List;

import com.revature.sealTheDeal.dao.EmployeeDAO;
import com.revature.sealTheDeal.dao.GuestDAO;
import com.revature.sealTheDeal.models.Guest;

public class GuestServices {
	private final GuestDAO guestDAO;

	public GuestServices(GuestDAO guestDAO) {
		this.guestDAO = guestDAO;
	}

	public boolean addEmployee(Guest guest) {
		return guestDAO.addGuest(guest);
	}

	public List<Guest> getAllGuests() {
		return guestDAO.getAllGuests();
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
}

