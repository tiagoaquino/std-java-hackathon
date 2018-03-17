package com.skipthedishes.dao.impl;

import static com.skipthedishes.utils.StDUtils.likeFormat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.skipthedishes.dao.ProductDAO;
import com.skipthedishes.entity.Product;

@Repository
@Qualifier("mysqlhibernate")
@Transactional
public class ProductDAOImpl implements ProductDAO  {
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Product> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);

		Root<Product> rootEntry = cq.from(Product.class);
		CriteriaQuery<Product> all = cq.select(rootEntry);

		TypedQuery<Product> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	@Override
	public Product getById(Long id) {
		return em.find(Product.class, id);
	}

	@Override
	public void delete(Long id) {
		Product product = getById(id);
		if (product != null) {
			em.remove(product);
		}
	}

	@Override
	public void update(Product product) {
		em.merge(product);
	}

	@Override
	public void insert(Product product) {
		em.merge(product);
	}

	@Override
	public List<Product> searchProducts(String searchText) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);

		Root<Product> rootEntry = cq.from(Product.class);
		CriteriaQuery<Product> all = cq.select(rootEntry);
		Path<String> pathName = rootEntry.get("name");
		
		Predicate likeName = cb.like(pathName, likeFormat(searchText));
		
		cq.where(likeName);
	    cq.orderBy(cb.asc(pathName));

		TypedQuery<Product> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}
	


}
