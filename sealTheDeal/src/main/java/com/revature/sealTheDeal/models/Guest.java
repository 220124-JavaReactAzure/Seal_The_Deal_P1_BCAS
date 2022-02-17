package com.revature.sealTheDeal.models;

public class Guest extends User {

	public Guest(String username, String firstName, String lastName, String password, String user_email, int accountType, String weddingName) {
		super(username, firstName, lastName, password, user_email, accountType);
		// TODO Auto-generated constructor stub
	
	}
	private int guestId;
	private String  weddingPartyName;
	
	
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public String getWeddingPartyName() {
		return weddingPartyName;
	}
	public void setWeddingPartyName(String weddingPartyName) {
		this.weddingPartyName = weddingPartyName;
	}
}