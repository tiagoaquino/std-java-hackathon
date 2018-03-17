package com.skipthedishes.dao;

import java.util.List;

import com.skipthedishes.entity.Order;

public interface OrderDAO {

	void insert(Order order);
	
	Order getById(Long id);
	
	void update(Order order);

	List<Order> getAllCustomerOrders(Long idCustomer);

}
