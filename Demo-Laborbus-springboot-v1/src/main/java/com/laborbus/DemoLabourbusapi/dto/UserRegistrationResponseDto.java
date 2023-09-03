package com.laborbus.DemoLabourbusapi.dto;

public class UserRegistrationResponseDto {

	private OtpStatus status;
	private String message;

	public UserRegistrationResponseDto() {
		super();

	}

	public UserRegistrationResponseDto(OtpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public OtpStatus getStatus() {
		return status;
	}

	public void setStatus(OtpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
