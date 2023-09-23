package com.jsp.OnlinePharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor
// instead of generating constructors using fields this annotation is used
public class MedicalStoreIdNotFoundException extends RuntimeException {
	private String message;
	
}
