package com.jsp.OnlinePharmacy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MedicalStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storeId;
	private String storeName;
	private String managerName;
	private long phone;
	
	@ManyToOne
	private Admin admin;
	
	@OneToOne
	private Address address;
}
