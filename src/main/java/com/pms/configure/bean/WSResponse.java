package com.pms.configure.bean;

import java.util.UUID;

public class WSResponse {
	private String outcome;
	private String message;
	private UUID id;
	private String code;
	private String mode;
	private Object data;
	private String[] error;

	public static WSResponse createSuccess(String msg, UUID id, String mode, Object data) {
		WSResponse wsResponse = new WSResponse();
		wsResponse.outcome = "success";
		wsResponse.message = msg;
		wsResponse.id = id;
		wsResponse.mode = mode;
		wsResponse.data = data;
		return wsResponse;
	}

	public static WSResponse createSuccess(String msg, UUID id, Object data) {
		WSResponse wsResponse = new WSResponse();
		wsResponse.outcome = "success";
		wsResponse.message = msg;
		wsResponse.id = id;
		wsResponse.data = data;
		return wsResponse;
	}

	public static WSResponse createSuccess(String msg, Object data) {
		WSResponse wsResponse = new WSResponse();
		wsResponse.outcome = "success";
		wsResponse.message = msg;
		wsResponse.data = data;
		return wsResponse;
	}

	public static WSResponse createFailure(String msg, String[] error) {
		WSResponse wsResponse = new WSResponse();
		wsResponse.outcome = "error";
		wsResponse.message = msg;
		wsResponse.error = error;
		return wsResponse;
	}

	public String[] getError() {
		return error;
	}

	public void setError(String[] error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public WSResponse(String outcome, String message, UUID id, String code, String mode, Object data) {
		super();
		this.outcome = outcome;
		this.message = message;
		this.id = id;
		this.code = code;
		this.mode = mode;
		this.data = data;
	}

	public WSResponse(String outcome, String message, UUID id, String code, String mode, Object data, String[] error) {
		super();
		this.outcome = outcome;
		this.message = message;
		this.id = id;
		this.code = code;
		this.mode = mode;
		this.data = data;
		this.error = error;
	}

	public WSResponse() {
		super();
	}

	public String getOutcome() {
		return outcome;
	}

	public String getMessage() {
		return message;
	}

	public UUID getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getMode() {
		return mode;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	/*
	 * @Override public boolean equals(Object obj) {
	 * 
	 * return this.equals(obj); }
	 */

}
