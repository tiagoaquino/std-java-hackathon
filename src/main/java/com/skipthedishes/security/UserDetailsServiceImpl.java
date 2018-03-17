package com.skipthedishes.security;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skipthedishes.entity.Customer;
import com.skipthedishes.service.CustomerService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomerService customerService;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerService.findByEmail(username);
		
		if (customer == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(customer.getEmail(), customer.getPassword(), emptyList());
	}
}