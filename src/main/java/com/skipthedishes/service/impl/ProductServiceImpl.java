package com.skipthedishes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.skipthedishes.dao.ProductDAO;
import com.skipthedishes.entity.Product;
import com.skipthedishes.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("mysqlhibernate")
	private ProductDAO productDao;

	@Override
	public List<Product> getAll() {
		return productDao.getAll();
	}

	@Override
	public Product getById(Long id) {
		return productDao.getById(id);
	}

	@Override
	public void delete(Long id) {
		productDao.delete(id);
	}

	@Override
	public void update(Product Product) {
		productDao.update(Product);
	}

	@Override
	public void insert(Product Product) {
		productDao.insert(Product);
	}

	@Override
	public List<Product> searchProducts(String searchText) {
		return productDao.searchProducts(searchText);
	}

}
