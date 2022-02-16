package com.revature.sealTheDeal.models;

public class WeddingUser extends User {

	public WeddingUser(String username, String firstName, String lastName, String pass, String user_email,
			int accountType) {
		super(username, firstName, lastName, pass, user_email, accountType);
		// TODO Auto-generated constructor stub
	}

	private String weddingPartyName;
	private String petTypeGroom;
	private String petNameGroom;
	private String petTypeBride;
	private String petNameBride;
	private int dayOfWedding;
	private double weddingCost;
	
	public String getWeddingPartyName() {
		return weddingPartyName;
	}

	public void setWeddingPartyName(String weddingPartyName) {
		this.weddingPartyName = weddingPartyName;
	}

	public String getPetTypeGroom() {
		return petTypeGroom;
	}

	public void setPetTypeGroom(String petTypeGroom) {
		this.petTypeGroom = petTypeGroom;
	}

	public String getPetNameGroom() {
		return petNameGroom;
	}

	public void setPetNameGroom(String petNameGroom) {
		this.petNameGroom = petNameGroom;
	}

	public String getPetTypeBride() {
		return petTypeBride;
	}

	public void setPetTypeBride(String petTypeBride) {
		this.petTypeBride = petTypeBride;
	}

	public String getPetNameBride() {
		return petNameBride;
	}

	public void setPetNameBride(String petNameBride) {
		this.petNameBride = petNameBride;
	}

	public int getDayOfWedding() {
		return dayOfWedding;
	}

	public void setDayOfWedding(int dayOfWedding) {
		this.dayOfWedding = dayOfWedding;
	}

	public double getWeddingCost() {
		return weddingCost;
	}

	public void setWeddingCost(double weddingCost) {
		this.weddingCost = weddingCost;
	}
	

}
