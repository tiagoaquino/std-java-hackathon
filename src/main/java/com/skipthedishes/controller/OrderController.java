package com.skipthedishes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.entity.Order;
import com.skipthedishes.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Order order) {
		orderService.insert(order);
	}

	@RequestMapping(value = "/cancel/{idOrder}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cancelOrder(@PathVariable("idOrder") Long idOrder) {
		orderService.cancelOrder(idOrder);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public List<Order> getAllCustomerOrders() {
		return orderService.getAllCustomerOrders();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Order getById(@PathVariable("id") Long id) {
		return orderService.getById(id);
	}

}
