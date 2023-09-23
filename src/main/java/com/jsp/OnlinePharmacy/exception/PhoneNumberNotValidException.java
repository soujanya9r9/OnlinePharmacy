package com.jsp.OnlinePharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhoneNumberNotValidException extends RuntimeException{
	private String message;
}
