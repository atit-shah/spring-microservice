package com.learn.cloud.cart.dao;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learn.cloud.cart.dao.repository.cart.CartRepository;
import com.learn.cloud.cart.dto.request.CartItemRequest;
import com.learn.cloud.cart.model.CartItem;
import com.learn.cloud.cart.model.MyCart;

@Repository
class CartDAOImpl implements CartDAO {

	private CartRepository cartRepository;

	@Autowired
	public CartDAOImpl(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	}

	@Override
	public MyCart getMyCart(int customerId) {
		return cartRepository.findById(customerId);
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, transactionManager = "cartDBTransactionManager")
	@Transactional(propagation = Propagation.REQUIRED, transactionManager = "cartDBTransactionManager")
	public CartItem addItemToCart(int customerId, CartItemRequest cardItemRequest) {
		MyCart myCart = this.getMyCart(customerId);
		if (myCart == null) {
			myCart = preprateCartInstance(customerId);
		}
		Set<CartItem> cartItemSet = myCart.getItems();
		if (cartItemSet == null) {
			cartItemSet = new LinkedHashSet<>();
		}
		CartItem cartItem = preprateCartItemInstance(myCart, cardItemRequest);
		cartItemSet.add(cartItem);
		cartRepository.save(myCart);
		return cartItem;
	}

	private CartItem preprateCartItemInstance(MyCart myCart, CartItemRequest cardItemRequest) {
		CartItem cartItem = new CartItem();
		cartItem.setMyCart(myCart);
		cartItem.setProductId(cardItemRequest.getProductId());
		cartItem.setQuantity(cardItemRequest.getQuantity());
		return cartItem;
	}

	private MyCart preprateCartInstance(int customerId) {
		MyCart myCart = new MyCart();
		myCart.setCustomerId(customerId);
		myCart.setTransactionId(UUID.randomUUID().toString());
		return myCart;
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, transactionManager = "cartTransactionManager")
	@Transactional(propagation = Propagation.REQUIRED, transactionManager = "cartDBTransactionManager")
	public boolean removeItemFromCart(int customerId, CartItemRequest cardItemRequest) {
		boolean success = false;
		MyCart myCart = this.getMyCart(customerId);
		if (myCart != null) {
			Set<CartItem> cartItemSet = myCart.getItems();
			if (cartItemSet != null) {
				Iterator<CartItem> itr = cartItemSet.iterator();
				while (itr.hasNext()) {
					CartItem cartItem = itr.next();
					if (cartItem.getProductId().equals(cardItemRequest.getProductId())) {
						itr.remove();
						success = true;
					}
				}
			}
		}
		if (success) {
			cartRepository.save(myCart);
		}
		return success;
	}

}
