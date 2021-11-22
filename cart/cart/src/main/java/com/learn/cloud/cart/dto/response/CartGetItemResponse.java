package com.learn.cloud.cart.dto.response;

import java.util.List;

public class CartGetItemResponse {

	private String transactionId;
	private List<CartItem> cardItems;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public List<CartItem> getCardItems() {
		return cardItems;
	}

	public void setCardItems(List<CartItem> cardItems) {
		this.cardItems = cardItems;
	}

	public static class CartItem {
		private String productId;
		private String productName;
		private int qauntity;

		public CartItem() {

		}

		public CartItem(String productId, String productName, int qauntity) {
			super();
			this.productId = productId;
			this.productName = productName;
			this.qauntity = qauntity;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public int getQauntity() {
			return qauntity;
		}

		public void setQauntity(int qauntity) {
			this.qauntity = qauntity;
		}

	}

}
