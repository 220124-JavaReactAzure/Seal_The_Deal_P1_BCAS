package com.revature.sealTheDeal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking")

public class Booking {

	@Id
	@Column(name = "service_name")
	private String serviceName;

	@Column(name = "service_type")
	private int serviceType;

	@Column(name = "price")
	private double price;

	@Column(name = "date")
	private boolean isBooked;

	public Booking() {

		super();
	}

	public Booking(String serviceName, int serviceType, double price, boolean isBooked) {
		super();
		this.serviceName = serviceName;
		this.serviceType = serviceType;
		this.price = price;
		this.isBooked = isBooked;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

}
