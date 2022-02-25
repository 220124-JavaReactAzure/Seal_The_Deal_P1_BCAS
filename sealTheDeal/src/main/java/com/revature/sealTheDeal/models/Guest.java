package com.revature.sealTheDeal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "guestinfo")
@PrimaryKeyJoinColumn(name = "username")
public class Guest extends User {
	@Column(name = "wedding_party_name")
	private String weddingPartyName;
	
	@Column(name = "food_type")
	private String foodType;
	
	@Column(name = "plus_one_name")
	private String plusOne;
	
	@Column(name = "plus_one_food_type")
	private String plusOneFoodType;
	
	@Column(name = "is_attending")
	private boolean isAttending;
	
	public Guest() {
		super();
	}
	
	public Guest(String username, String firstName, String lastName, String pass, String user_email, int accountType, String weddingPartyName, String foodType, String plusOne, String plusOneFoodType, boolean isAttending) {
		super(username, firstName, lastName, pass, user_email, accountType);
		this.weddingPartyName = weddingPartyName;
		this.foodType = foodType;
		this.plusOne = plusOne;
		this.plusOneFoodType = plusOneFoodType;
		this.isAttending = isAttending;
	}
	

	public String getWeddingPartyName() {
		return weddingPartyName;
	}
	
	public void setWeddingPartyName(String weddingPartyName) {
		this.weddingPartyName = weddingPartyName;
	}

	public String getFoodType() {
		return foodType;
	}


	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}


	public String getPlusOne() {
		return plusOne;
	}


	public void setPlusOne(String plusOne) {
		this.plusOne = plusOne;
	}
	
	public boolean isAttending() {
		return isAttending;
	}

	public void setAttendance(boolean isAttending) {
		this.isAttending = isAttending;
	}

	public String getPlusOneFoodType() {
		return plusOneFoodType;
	}

	public void setPlusOneFoodType(String plusOneFoodType) {
		this.plusOneFoodType = plusOneFoodType;
	}

}