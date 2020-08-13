package com.cinema.sravs.error;

public class ErrorField {
	private final String field, code;

	public ErrorField(String field, String code) {
		this.field = field;
		this.code = code;
	}

	public String getField() {
		return field;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "ErrorField [field=" + field + ", code=" + code + "]";
	}

}