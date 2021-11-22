package com.learn.cloud.cart.service;

import com.learn.cloud.cart.dto.request.CartItemRequest;
import com.learn.cloud.cart.dto.response.CartGetItemResponse;
import com.learn.cloud.cart.dto.response.CartItemResponse;
import com.learn.cloud.cart.util.ApplicationResponse;

public interface CartService {

	ApplicationResponse<CartGetItemResponse> getItems();

	ApplicationResponse<CartItemResponse> addItemToCart(CartItemRequest cardItemRequest);

	ApplicationResponse<CartItemResponse> removeItemFromCart(CartItemRequest cardItemRequest);

}
