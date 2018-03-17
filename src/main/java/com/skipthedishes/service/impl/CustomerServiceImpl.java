package com.skipthedishes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.skipthedishes.dao.CustomerDAO;
import com.skipthedishes.entity.Customer;
import com.skipthedishes.exceptions.ExistingUserException;
import com.skipthedishes.security.JWTBuilder;
import com.skipthedishes.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
	private JWTBuilder jwtBuilder;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public String signUp(Customer customer) {
		// Check existing user
		Customer existing = customerDao.findByEmail(customer.getEmail());
		
		if (existing != null) {
			throw new ExistingUserException("There is already an account with this email!");
		}
		
		// Encrypt password
		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
		
		// Create user
		customerDao.insert(customer);
		return jwtBuilder.generate(customer.getEmail());
	}

	@Override
	public Customer findByEmail(String email) {
		return customerDao.findByEmail(email);
	}


}
