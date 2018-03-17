package com.skipthedishes.service;

import java.util.List;

import com.skipthedishes.entity.Order;

public interface OrderService {
	
	void insert(Order entity);

	void cancelOrder(Long idOrder);

	List<Order> getAllCustomerOrders();

	Order getById(Long id);

}
