package com.learn.cloud.cart.dto.response;

public class CartItemResponse {

	private String productId;
	private int quantity;
	private boolean result;

	public CartItemResponse() {
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "CartItemResponse [productId=" + productId + ", quantity=" + quantity + ", result=" + result + "]";
	}

}
