package com.jsp.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.repository.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo repo;
	
	public Address saveAddress(Address address) {
		return repo.save(address);
	}
	
	public Address findAddressById(int addressId) {
		Optional<Address> optinal = repo.findById(addressId);
		if(optinal.isPresent()) {
			return optinal.get();
		}
		return null;
	}
	
	public Address updateAddressById(int addreId, Address address) {
		Optional<Address> optional = repo.findById(addreId);
		if(optional.isEmpty()) {
			return null;
		}
		address.setAddressId(addreId);
		return repo.save(address);
	}
	
	public Address deleteAddressById(int addressId) {
		Optional<Address> optional = repo.findById(addressId);
		if(optional.isPresent()) {
			optional.get();
			Address address = optional.get();
			repo.delete(address);
			return address;
		}
		return null;
	}
}
