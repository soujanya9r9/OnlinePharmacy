package com.jsp.OnlinePharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingCannotBeCancelledNow extends RuntimeException{
	private String message;
}
