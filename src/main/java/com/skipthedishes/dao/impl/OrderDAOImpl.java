package com.skipthedishes.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skipthedishes.dao.OrderDAO;
import com.skipthedishes.entity.Customer;
import com.skipthedishes.entity.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void insert(Order order) {
		em.merge(order);
	}

	@Override
	public Order getById(Long id) {
		return em.find(Order.class, id);
	}

	@Override
	@Transactional
	public void update(Order order) {
		em.merge(order);
	}

	@Override
	public List<Order> getAllCustomerOrders(Long idCustomer) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);

		Root<Order> rootEntry = cq.from(Order.class);
		CriteriaQuery<Order> all = cq.select(rootEntry);
		Join<Order, Customer> joinCustomer = rootEntry.join("customer");
		Path<Long> pathCustomer = joinCustomer.get("id");
		
		Predicate eqCustomer = cb.equal(pathCustomer, idCustomer);
		
		cq.where(eqCustomer);

		TypedQuery<Order> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

}
