package com.learn.cloud.cart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.cloud.cart.dto.request.CartItemRequest;
import com.learn.cloud.cart.dto.response.CartGetItemResponse;
import com.learn.cloud.cart.dto.response.CartItemResponse;
import com.learn.cloud.cart.service.CartService;
import com.learn.cloud.cart.util.ApplicationResponse;

@RestController
@RequestMapping("/cart")
public class CartController {

	private CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@GetMapping("/items")
	public ResponseEntity<ApplicationResponse<CartGetItemResponse>> getItems() {
		return ResponseEntity.ok(cartService.getItems());
	}

	@PostMapping("/item/add")
	public ResponseEntity<ApplicationResponse<CartItemResponse>> addItemToCart(
			@Valid @RequestBody CartItemRequest cardItemRequest) {
		return ResponseEntity.ok(cartService.addItemToCart(cardItemRequest));
	}

	@PostMapping("/item/remove")
	public ResponseEntity<ApplicationResponse<CartItemResponse>> removeItemFromCart(
			@Valid @RequestBody CartItemRequest cardItemRequest) {
		return ResponseEntity.ok(cartService.removeItemFromCart(cardItemRequest));
	}

}
