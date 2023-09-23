package com.jsp.OnlinePharmacy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String customerName;
	private String customerEmail;
	private String password;
	private long phoneNumber;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Address> addresses;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Booking> bookings;
}
