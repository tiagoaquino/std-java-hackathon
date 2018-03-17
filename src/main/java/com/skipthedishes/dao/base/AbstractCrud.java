package com.skipthedishes.dao.base;

import java.util.List;

import com.skipthedishes.entity.BaseEntity;

/**
 * This is just an example how to reuse common codes on cruds.
 * @author tiago
 *
 * @param <E>
 */
public interface AbstractCrud<E extends BaseEntity> {

	List<E> getAll();

	E getById(Long id);

	void delete(Long id);

	void update(E entity);

	void insert(E entity);

}
