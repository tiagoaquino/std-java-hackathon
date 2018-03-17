package com.skipthedishes.builders;

import com.skipthedishes.entity.Cousine;
import com.skipthedishes.entity.Store;

public class StoreBuilder {

	private Long id;

	private String name;

	private String address;

	private Cousine cousine;

	public StoreBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public StoreBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public StoreBuilder withAddress(String address) {
		this.address = address;
		return this;
	}

	public StoreBuilder withCousine(Cousine cousine) {
		this.cousine = cousine;
		return this;
	}

	public Store build() {
		Store store = new Store();
		store.setId(id);
		store.setName(name);
		store.setAddress(address);
		store.setCousine(cousine);
		return store;
	}

}
