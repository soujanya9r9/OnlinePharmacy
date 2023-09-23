package com.jsp.OnlinePharmacy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int addressId;
	private String streetName;
	private String city;
	private String state;
	private int pincode;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
}
