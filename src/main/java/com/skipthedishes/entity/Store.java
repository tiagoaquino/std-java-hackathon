package com.skipthedishes.entity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Store extends BaseEntity {

	private String name;
	
	private String address;
	
	@ManyToOne(fetch = LAZY)
	private Cousine cousine;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Cousine getCousine() {
		return cousine;
	}

	public void setCousine(Cousine cousine) {
		this.cousine = cousine;
	}

}
