package com.skipthedishes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.entity.Product;
import com.skipthedishes.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return productService.getAll();
	}
	
	@RequestMapping(value = "search/{searchText}", method = RequestMethod.GET)
	public List<Product> searchProducts(@PathVariable("searchText") String searchText) {
		
		return productService.searchProducts(searchText);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product getById(@PathVariable("id") Long id) {
		return productService.getById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		productService.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable("id") Long id, @RequestBody Product Product) {
		productService.update(Product);
	}
	
	@RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Product Product) {
		productService.insert(Product);
	}

}
