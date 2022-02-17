package com.revature.sealTheDeal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "weddinginfo")
@PrimaryKeyJoinColumn(name = "username")
public class WeddingUser extends User {

	@Column(name = "wedding_party_name")
	private String weddingPartyName;
	
	@Column(name = "pet_type_groom")
	private String petTypeGroom;
	
	@Column(name = "pet_name_groom")
	private String petNameGroom;
	
	@Column(name = "pet_type_bride")
	private String petTypeBride;
	
	@Column(name = "pet_name_bride")
	private String petNameBride;
	
	@Column(name = "day_of_wedding")
	private int dayOfWedding;
	
	@Column(name = "wedding_cost")
	private double weddingCost;
	
	public WeddingUser() {
		
	}
	
	public WeddingUser(String username, String firstName, String lastName, String pass, String user_email,
			int accountType, String weddingPartyName, String petTypeGroom, String petNameGroom, String petTypeBride, String petNameBride, int dayOfWedding, double weddingCost) {
		super(username, firstName, lastName, pass, user_email, accountType);
		this.weddingPartyName = weddingPartyName;
		this.petTypeGroom = petTypeGroom;
		this.petNameGroom = petNameGroom;
		this.petTypeBride = petTypeBride;
		this.petNameBride = petNameBride;
		this.dayOfWedding = dayOfWedding;
		this.weddingCost = weddingCost;
	}
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
