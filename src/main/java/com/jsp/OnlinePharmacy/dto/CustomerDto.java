package com.jsp.OnlinePharmacy.dto;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private int customerId;
	private String customerName;
	private String customerEmail;
	private long phoneNumber;
	
	@ManyToMany
	private List<AddressDto> addresses;
	
	@OneToMany
	private List<BookingDto> bookingsDtos;
}
