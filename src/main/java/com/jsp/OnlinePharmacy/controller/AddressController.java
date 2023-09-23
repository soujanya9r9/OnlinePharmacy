package com.jsp.OnlinePharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlinePharmacy.dto.AddressDto;
import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.service.AddressService;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(@RequestBody Address address) {
		ResponseEntity<ResponseStructure<AddressDto>> entity = service.saveAddress(address);
		System.out.println(entity);
		return entity;
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AddressDto>> findAddressById(@RequestParam int addressId){
		return service.findAddressById(addressId);
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<AddressDto>> updateAddressById(@RequestParam int addressId, @RequestBody Address address){
		return service.updateAddressById(addressId, address);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddressById(@RequestParam int addressId){
		return service.deleteAddressById(addressId);
	}
}
