package com.skipthedishes.service;

import com.skipthedishes.entity.Customer;

public interface CustomerService {

	String signUp(Customer customer);

	Customer findByEmail(String email);

}
