package com.learn.cloud.cart.dto;

import java.util.Date;
import java.util.List;

public class ProductResponse {

	private String eventId;
	private Date eventTime;
	private int responseCode;
	private String responseMsg;
	private List<Product> responseObject;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public List<Product> getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(List<Product> responseObject) {
		this.responseObject = responseObject;
	}

	@Override
	public String toString() {
		return "ProductResponse [eventId=" + eventId + ", eventTime=" + eventTime + ", responseCode=" + responseCode
				+ ", responseMsg=" + responseMsg + ", responseObject=" + responseObject + "]";
	}

	public static class Product {
		private String id;
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
