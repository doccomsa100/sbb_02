package com.mysite.sbb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 사용자 정의 예외클래스
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public DataNotFoundException(String message) {
		super(message);
	}
}
