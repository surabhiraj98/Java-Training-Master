package com.rakuten.training.web;

public class RegistrationError {

	String passwordString;

	String errorMessageString;

	public RegistrationError() {

	}

	public String getErrorMessageString() {
		return errorMessageString;
	}

	public void setErrorMessageString(String errorMessageString) {
		this.errorMessageString = errorMessageString;
	}

	public RegistrationError(String errorMessage) {
		this.errorMessageString = errorMessage;
	}

}
