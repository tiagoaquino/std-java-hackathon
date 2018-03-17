package com.skipthedishes.builders;

import java.math.BigDecimal;

import com.skipthedishes.entity.Product;
import com.skipthedishes.entity.Store;

@SuppressWarnings("unused")
public class ProductBuilder {

	private Long id;

	private Store store;

	private String name;

	private String description;

	private BigDecimal price;

	public ProductBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public ProductBuilder withStore(Store store) {
		this.store = store;
		return this;
	}

	public ProductBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ProductBuilder withDescription(String description) {
		this.description = description;
		return this;
	}

	public ProductBuilder withPrice(BigDecimal price) {
		this.price = price;
		return this;
	}
	
	public Product build() {
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		return product;
	}

}
