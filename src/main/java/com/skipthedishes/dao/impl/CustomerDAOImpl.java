package com.skipthedishes.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.skipthedishes.dao.CustomerDAO;
import com.skipthedishes.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Customer getById(Long id) {
		return em.find(Customer.class, id);
	}

	@Override
	public Customer findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

		Root<Customer> rootEntry = cq.from(Customer.class);
		CriteriaQuery<Customer> all = cq.select(rootEntry);
		Path<String> pathEmail = rootEntry.get("email");

		Predicate likeName = cb.equal(pathEmail, email);

		cq.where(likeName);

		TypedQuery<Customer> query = em.createQuery(all);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	@Transactional
	public void insert(Customer customer) {
		em.merge(customer);
	}

}
