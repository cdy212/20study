package com.study20.web.part0.controller;

public enum PushMessage {
	
	ticker("ticker"),
	msg("msg"),
	title("title");
	
	private String value;
	
	private PushMessage(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
