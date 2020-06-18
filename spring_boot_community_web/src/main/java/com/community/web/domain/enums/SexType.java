package com.community.web.domain.enums;

public enum SexType {
	MALE("남성"), FEMALE("여성");

	private String value;

	SexType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
