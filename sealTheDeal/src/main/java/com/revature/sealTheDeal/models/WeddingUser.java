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
	
	@Column(name = "wedding_budget")
	private double weddingBudget;
		
	@Column(name = "wedding_cost")
	private double weddingCost;
	
	@Column(name = "booked_caterer")
	private String bookedCaterer;
	
	@Column(name = "booked_florist")
	private String bookedFlorist;
	
	@Column(name = "booked_musician")
	private String bookedMusician;
	
	@Column(name = "booked_photographer")
	private String bookedPhotographer;
	
	@Column(name = "booked_venue")
	private String bookedVenue;
	

	
	public WeddingUser() {
		
	}
	
	public WeddingUser(String username, String firstName, String lastName, String pass, String user_email,
			int accountType, String weddingPartyName, String petTypeGroom, String petNameGroom, String petTypeBride, String petNameBride, int dayOfWedding, double weddingBudget, double weddingCost,
			String bookedCaterer,  String bookedFlorist, String bookedMusician, String bookedPhotographer, String bookedVenue) {
		super(username, firstName, lastName, pass, user_email, accountType);
		this.weddingPartyName = weddingPartyName;
		this.petTypeGroom = petTypeGroom;
		this.petNameGroom = petNameGroom;
		this.petTypeBride = petTypeBride;
		this.petNameBride = petNameBride;
		this.dayOfWedding = dayOfWedding;
		this.weddingBudget = weddingBudget;
		this.weddingCost = weddingCost;
		this.bookedCaterer = bookedCaterer;
		this.bookedFlorist = bookedFlorist;
		this.bookedMusician = bookedMusician;
		this.bookedPhotographer = bookedPhotographer;
		this.bookedVenue = bookedVenue;
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

	public double getWeddingBudget() {
		return weddingBudget;
	}

	public void setWeddingBudget(double weddingBudget) {
		this.weddingBudget = weddingBudget;
	}

	public double getWeddingCost() {
		return weddingCost;
	}

	public void setWeddingCost(double weddingCost) {
		this.weddingCost = weddingCost;
	}

	public String getBookedCaterer() {
		return bookedCaterer;
	}

	public void setBookedCaterer(String bookedCaterer) {
		this.bookedCaterer = bookedCaterer;
	}

	public String getBookedFlorist() {
		return bookedFlorist;
	}

	public void setBookedFlorist(String bookedFlorist) {
		this.bookedFlorist = bookedFlorist;
	}

	public String getBookedMusician() {
		return bookedMusician;
	}

	public void setBookedMusician(String bookedMusician) {
		this.bookedMusician = bookedMusician;
	}

	public String getBookedPhotographer() {
		return bookedPhotographer;
	}

	public void setBookedPhotographer(String bookedPhotographer) {
		this.bookedPhotographer = bookedPhotographer;
	}

	public String getBookedVenue() {
		return bookedVenue;
	}

	public void setBookedVenue(String bookedVenue) {
		this.bookedVenue = bookedVenue;
	}
	

}
