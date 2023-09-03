package com.laborbus.DemoLabourbusapi.dto;

public class UserRegistrationRequestDto {

	private String mobileNumber;
	private String password;
	private String oneTimePassword;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOneTimePassword() {
		return oneTimePassword;
	}

	public void setOneTimePassword(String oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}

	public UserRegistrationRequestDto(String mobileNumber, String password, String oneTimePassword) {
		super();
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.oneTimePassword = oneTimePassword;
	}

	public UserRegistrationRequestDto() {
		super();

	}

}
