package com.learn.cloud.cart.dao;

import org.springframework.data.jpa.repository.Query;

import com.learn.cloud.cart.dto.request.CartItemRequest;
import com.learn.cloud.cart.model.CartItem;
import com.learn.cloud.cart.model.MyCart;

public interface CartDAO {

	@Query("select mc from MyCart mc left join fetch mc.items where customerId=?1")
	MyCart getMyCart(int customerId);

	CartItem addItemToCart(int customerId, CartItemRequest cardItemRequest);

	boolean removeItemFromCart(int customerId, CartItemRequest cardItemRequest);

}