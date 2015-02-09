package com.study20.web.part0.controller;

public class PushInfo {
	private String regId;
	private String[] arlRegId;
	private String userId;
	private String userName;
	private String title;
	private String ticker;
	private String message;
	
	public String[] getArlRegId() {
		return arlRegId;
	}
	public void setArlRegId(String[] arlRegId) {
		this.arlRegId = arlRegId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
