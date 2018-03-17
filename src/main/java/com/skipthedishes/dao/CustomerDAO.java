package com.skipthedishes.dao;

import com.skipthedishes.entity.Customer;

public interface CustomerDAO {
	
	Customer getById(Long id);

	Customer findByEmail(String email);

	void insert(Customer customer);

}
