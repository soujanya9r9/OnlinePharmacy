package com.jsp.OnlinePharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.AddressDao;
import com.jsp.OnlinePharmacy.dto.AddressDto;
import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.exception.AddressIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(Address address){
		Address dbAddress = addressDao.saveAddress(address);
		AddressDto dto = new AddressDto();
		dto.setAddressId(dbAddress.getAddressId());
		dto.setCity(dbAddress.getCity());
		dto.setState(dbAddress.getState());
		dto.setStreetName(dbAddress.getStreetName());
		dto.setPincode(dbAddress.getPincode());
		
		ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
		structure.setMessage("Address Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<AddressDto>> findAddressById(int addressId){
		Address dbAddress = addressDao.findAddressById(addressId);
		if(dbAddress != null) {
			AddressDto dto = new AddressDto();
			dto.setAddressId(dbAddress.getAddressId());
			dto.setCity(dbAddress.getCity());
			dto.setState(dbAddress.getState());
			dto.setStreetName(dbAddress.getStreetName());
			dto.setPincode(dbAddress.getPincode());
			
			ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
			structure.setMessage("Address Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.FOUND);
		}
		else {
			throw new AddressIdNotFoundException("Sorry Failed to find the ID");
		}
	}
	
	public ResponseEntity<ResponseStructure<AddressDto>> updateAddressById(int addressId, Address address){
		Address dbAddress = addressDao.updateAddressById(addressId, address);
		if(dbAddress != null) {
			AddressDto dto = new AddressDto();
			dto.setAddressId(dbAddress.getAddressId());
			dto.setCity(dbAddress.getCity());
			dto.setState(dbAddress.getState());
			dto.setStreetName(dbAddress.getStreetName());
			dto.setPincode(dbAddress.getPincode());
			
			ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
			structure.setMessage("Address Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.OK);
		}
		else {
			throw new AddressIdNotFoundException("Sorry Failed to find the ID");
		}
	}
	
	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddressById(int addressId){
		Address dbAddress = addressDao.deleteAddressById(addressId);
		if(dbAddress != null) {
			AddressDto dto = new AddressDto();
			dto.setAddressId(dbAddress.getAddressId());
			dto.setCity(dbAddress.getCity());
			dto.setState(dbAddress.getState());
			dto.setStreetName(dbAddress.getStreetName());
			dto.setPincode(dbAddress.getPincode());
			
			ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
			structure.setMessage("Address deleted Successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(dto); 
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.GONE);
		}
		else {
			throw new AddressIdNotFoundException("Sorry Failed to delete the ID");
		}
	}
}
