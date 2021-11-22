package com.learn.cloud.cart.dao.repository.cart;

import com.learn.cloud.cart.base.BaseRepository;
import com.learn.cloud.cart.model.MyCart;

public interface CartRepository extends BaseRepository<MyCart, Integer> {

	MyCart findById(Integer id);
	
}
