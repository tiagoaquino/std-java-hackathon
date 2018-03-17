package com.skipthedishes.dao;

import java.util.List;

import com.skipthedishes.dao.base.AbstractCrud;
import com.skipthedishes.entity.Product;

public interface ProductDAO extends AbstractCrud<Product> {

	List<Product> searchProducts(String searchText);

}
