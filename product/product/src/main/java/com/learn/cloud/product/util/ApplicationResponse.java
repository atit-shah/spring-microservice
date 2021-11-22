package com.learn.cloud.product.util;

import java.util.Date;
import java.util.UUID;

public class ApplicationResponse<T> {

	private String eventId;
	private Date eventTime;
	private int responseCode;
	private String responseMsg;
	private T responseObject;
	private String instanceId;

	public ApplicationResponse() {

	}

	public ApplicationResponse(int responseCode, String responseMsg, String instanceId) {
		this(UUID.randomUUID().toString(), new Date(), responseCode, responseMsg, null, instanceId);
	}

	public ApplicationResponse(String eventId, Date eventTime, int responseCode, String responseMsg, T responseObject,
			String instanceId) {
		super();
		this.eventId = eventId;
		this.eventTime = eventTime;
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
		this.responseObject = responseObject;
		this.instanceId = instanceId;
	}

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

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	@Override
	public String toString() {
		return "ApplicationResponse [eventId=" + eventId + ", eventTime=" + eventTime + ", responseCode=" + responseCode
				+ ", responseMsg=" + responseMsg + ", responseObject=" + responseObject + ", instanceId=" + instanceId
				+ "]";
	}

}
