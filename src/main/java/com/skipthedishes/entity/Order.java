package com.skipthedishes.entity;

import static javax.persistence.FetchType.LAZY;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skipthedishes.enums.OrderStatus;

@Entity(name = "tb_order")
public class Order extends BaseEntity {

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProductOrder> orderItems;

	private LocalDateTime date;

	@ManyToOne(fetch = LAZY)
	@JsonIgnore
	private Customer customer;

	private String contact;

	private BigDecimal total;

	private String deliveryAddress;

	private OrderStatus status;

	private LocalDateTime lastUpdate;

	public List<ProductOrder> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<ProductOrder> orderItems) {
		this.orderItems = orderItems;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public void setCustomerId(Long id) {
		Customer customer = new Customer();
		customer.setId(id);
		setCustomer(customer);
	}

}
