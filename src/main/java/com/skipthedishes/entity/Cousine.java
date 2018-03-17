package com.skipthedishes.entity;

import javax.persistence.Entity;

@Entity
public class Cousine extends BaseEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
