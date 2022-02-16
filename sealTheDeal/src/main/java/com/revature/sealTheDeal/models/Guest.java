package com.revature.sealTheDeal.models;

public class Guest extends User {

	private int guestId;
	private User user;
	private String  weddingPartyName;
	
	
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getWeddingPartyName() {
		return weddingPartyName;
	}
	public void setWeddingPartyName(String weddingPartyName) {
		this.weddingPartyName = weddingPartyName;
	}
}