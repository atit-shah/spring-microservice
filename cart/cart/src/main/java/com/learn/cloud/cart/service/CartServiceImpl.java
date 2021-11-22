package com.learn.cloud.cart.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learn.cloud.cart.dao.CartDAO;
import com.learn.cloud.cart.dto.ProductResponse;
import com.learn.cloud.cart.dto.request.CartItemRequest;
import com.learn.cloud.cart.dto.response.CartGetItemResponse;
import com.learn.cloud.cart.dto.response.CartItemResponse;
import com.learn.cloud.cart.model.CartItem;
import com.learn.cloud.cart.model.MyCart;
import com.learn.cloud.cart.remoteservice.ProductServiceProxy;
import com.learn.cloud.cart.util.ApplicationResponse;
import com.learn.cloud.cart.util.ApplicationResponseCode;
import com.learn.cloud.cart.util.ApplicationResponseMessage;

@Service
class CartServiceImpl implements CartService {

	private CartDAO cartDAO;

	private ProductServiceProxy productServiceProxy;

	private Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Value("${eureka.instance.instance-id}")
	private String instanceId;

	@Autowired
	public CartServiceImpl(CartDAO cartDAO, ProductServiceProxy productServiceProxy) {
		super();
		this.cartDAO = cartDAO;
		this.productServiceProxy = productServiceProxy;
	}

	@Override
	public ApplicationResponse<CartGetItemResponse> getItems() {
		int customerId = getCustomerId();
		ApplicationResponse<CartGetItemResponse> response = new ApplicationResponse<>(ApplicationResponseCode.SUCCESS,
				ApplicationResponseMessage.SUCCESS, instanceId);
		MyCart myCart = cartDAO.getMyCart(customerId);
		if (myCart != null) {
			CartGetItemResponse cartGetItemResponse = new CartGetItemResponse();
			cartGetItemResponse.setTransactionId(myCart.getTransactionId());
			List<String> productId = myCart.getItems().stream().map(cartItem -> cartItem.getProductId())
					.collect(Collectors.toList());
			List<ProductResponse.Product> productList = Collections.emptyList();
			if (!productId.isEmpty()) {
				ProductResponse remoteResponse = productServiceProxy.getProductsByIdIn(productId);
				productList = remoteResponse.getResponseObject();

			}
			Map<String, String> productMap = productList.stream()
					.collect(Collectors.toMap(ProductResponse.Product::getId, ProductResponse.Product::getName));
			cartGetItemResponse.setCardItems(myCart.getItems().stream()
					.map(cartItem -> new CartGetItemResponse.CartItem(cartItem.getProductId(),
							productMap.get(cartItem.getProductId()), cartItem.getQuantity()))
					.collect(Collectors.toList()));
			response.setResponseObject(cartGetItemResponse);
		}
		logger.info(String.format("response: %s", response.toString()));
		return response;
	}

	@Override
	public ApplicationResponse<CartItemResponse> addItemToCart(CartItemRequest cardItemRequest) {
		ApplicationResponse<CartItemResponse> applicationResponse = new ApplicationResponse<>(
				ApplicationResponseCode.UNKNOWN_ERROR, ApplicationResponseMessage.UNKNOWN_ERROR, instanceId);
		int customerId = getCustomerId();
		CartItem cartItem = cartDAO.addItemToCart(customerId, cardItemRequest);
		if (cartItem != null) {
			CartItemResponse cartItemResponse = new CartItemResponse();
			cartItemResponse.setProductId(cartItem.getProductId());
			cartItemResponse.setQuantity(cartItem.getQuantity());
			cartItemResponse.setResult(true);
			applicationResponse.setResponseCode(ApplicationResponseCode.SUCCESS);
			applicationResponse.setResponseMsg(ApplicationResponseMessage.SUCCESS);
			applicationResponse.setResponseObject(cartItemResponse);
		} else {
			applicationResponse.setResponseCode(ApplicationResponseCode.TRANSACTION_FAILURE);
			applicationResponse.setResponseMsg(ApplicationResponseMessage.TRANSACTION_FAILURE);
		}
		logger.info(String.format("response: %s", applicationResponse.toString()));
		return applicationResponse;
	}

	@Override
	public ApplicationResponse<CartItemResponse> removeItemFromCart(CartItemRequest cardItemRequest) {
		ApplicationResponse<CartItemResponse> applicationResponse = new ApplicationResponse<>(
				ApplicationResponseCode.UNKNOWN_ERROR, ApplicationResponseMessage.UNKNOWN_ERROR, instanceId);
		int customerId = getCustomerId();
		boolean result = cartDAO.removeItemFromCart(customerId, cardItemRequest);
		if (result) {
			CartItemResponse cartItemResponse = new CartItemResponse();
			applicationResponse.setResponseCode(ApplicationResponseCode.SUCCESS);
			applicationResponse.setResponseMsg(ApplicationResponseMessage.SUCCESS);
			cartItemResponse.setResult(result);
			applicationResponse.setResponseObject(cartItemResponse);
		} else {
			applicationResponse.setResponseCode(ApplicationResponseCode.TRANSACTION_FAILURE);
			applicationResponse.setResponseMsg(ApplicationResponseMessage.TRANSACTION_FAILURE);
		}
		logger.info(String.format("response: %s", applicationResponse.toString()));
		return applicationResponse;
	}

	private int getCustomerId() {
		return 1;
	}
}
