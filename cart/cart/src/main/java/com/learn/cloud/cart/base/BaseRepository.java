package com.learn.cloud.cart.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends Repository<T, ID> {

	T findById(ID id);

	T save(T t);

	void deleteById(ID id);

}
