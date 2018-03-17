package com.skipthedishes.service.impl;

import static com.skipthedishes.enums.OrderStatus.NEW;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.skipthedishes.dao.CustomerDAO;
import com.skipthedishes.dao.OrderDAO;
import com.skipthedishes.entity.Customer;
import com.skipthedishes.entity.Order;
import com.skipthedishes.enums.OrderStatus;
import com.skipthedishes.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDao;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public void insert(Order order) {
		LocalDateTime now = LocalDateTime.now();
		order.setDate(now);
		order.setLastUpdate(now);
		order.setStatus(NEW);
		order.getOrderItems().forEach(p -> p.setOrder(order));
		orderDao.insert(order);
	}

	@Override
	public void cancelOrder(Long idOrder) {
		Order order = orderDao.getById(idOrder);
		
		order.setStatus(OrderStatus.CANCELLED);
		order.setLastUpdate(LocalDateTime.now());
		
		orderDao.update(order);
	}

	@Override
	public List<Order> getAllCustomerOrders() {
		
		// Get logged user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) auth.getPrincipal();
		Customer customer = customerDAO.findByEmail(email);
		
		return orderDao.getAllCustomerOrders(customer.getId());
	}

	@Override
	public Order getById(Long id) {
		return orderDao.getById(id);
	}
	
}
