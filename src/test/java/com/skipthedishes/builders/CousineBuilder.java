package com.skipthedishes.builders;

import com.skipthedishes.entity.Cousine;

public class CousineBuilder {
	
	private Long id;
	
	private String name;

	public CousineBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public CousineBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public Cousine build() {
		Cousine cousine = new Cousine();
		cousine.setId(id);
		cousine.setName(name);
		return cousine;
	}

}
