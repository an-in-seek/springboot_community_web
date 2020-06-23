package com.community.web.domain.enums;

public enum ERole {
	ROLE_USER("사용자"),
    ROLE_MODERATOR("운영자"),
    ROLE_ADMIN("관리자");
    
    private String value;

	ERole(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}