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

import com.jsp.OnlinePharmacy.dto.CustomerDto;
import com.jsp.OnlinePharmacy.entity.Customer;
import com.jsp.OnlinePharmacy.service.CustomerService;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(@RequestParam int addressId,
			@RequestBody Customer customer) {
		return service.saveCustomer(addressId, customer);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@RequestBody Customer customer, @RequestParam int customerId){
		return service.updateCustomer(customer,customerId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerById(@RequestParam int customerId){
		return service.getCustomerById(customerId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomerById(@RequestParam int customerId){
		return service.deleteCustomerById(customerId);
	}

	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(@RequestParam String customerEmail, @RequestParam String password ){
		return service.loginCustomer(customerEmail, password);
	}
	
	@GetMapping("/forgotPassword")
	public ResponseEntity<ResponseStructure<CustomerDto>> forgotPassword(@RequestParam String customerEmail, @RequestParam long phoneNumber, @RequestParam String password){
		return service.forgotPassword(customerEmail, phoneNumber, password);
	}
}

