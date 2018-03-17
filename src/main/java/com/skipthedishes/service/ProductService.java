package com.skipthedishes.service;

import java.util.List;

import com.skipthedishes.dao.base.AbstractCrud;
import com.skipthedishes.entity.Product;

public interface ProductService extends AbstractCrud<Product> {

	List<Product> searchProducts(String searchText);

}
