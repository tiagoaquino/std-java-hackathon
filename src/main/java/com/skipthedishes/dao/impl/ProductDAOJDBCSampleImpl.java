package com.skipthedishes.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.skipthedishes.dao.ProductDAO;
import com.skipthedishes.entity.Product;


/**
 * This class is a example of another way do implement the DAO. Using JDBC.
 * @author tiago
 *
 */
@Repository
@Qualifier("mysqlspringjdbc")
public class ProductDAOJDBCSampleImpl implements ProductDAO {

	private RowMapper<Product> rowMapper = (rs, rowNum) -> {
		Product p = new Product();
		p.setId(rs.getLong("id"));
		p.setName(rs.getString("name"));
		return p;
	};

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void delete(Long id) {	
		jdbcTemplate.update("delete from todo where id = (?)", id);
	}

	@Override
	public List<Product> getAll() {
		return jdbcTemplate.query("select id, name from product", rowMapper);
	}

	@Override
	public Product getById(Long id) {
		return jdbcTemplate.queryForObject("select id, name from todo where id = (?)", rowMapper, id);
	}

	@Override
	public void update(Product product) {
		jdbcTemplate.update("update todo set name = (?) where id = (?)", product.getName(), product.getId());

	}

	@Override
	public void insert(Product product) {
		jdbcTemplate.update("INSERT INTO todo(name) VALUES (?)", product.getName());
	}

	@Override
	public List<Product> searchProducts(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

}
