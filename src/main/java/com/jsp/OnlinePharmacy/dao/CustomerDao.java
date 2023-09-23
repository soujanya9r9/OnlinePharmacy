package com.jsp.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.OnlinePharmacy.entity.Customer;
import com.jsp.OnlinePharmacy.repository.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo repo;
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer updateCustomer(Customer customer, int customerId) {
		Optional<Customer> optional = repo.findById(customerId);
		if(optional.isPresent()) {
			customer.setCustomerId(customerId);
			customer.setAddresses(optional.get().getAddresses());
			customer.setBookings(optional.get().getBookings());
			return repo.save(customer);
		}
		return null;
	}
	
	public Customer getCustomerById(int customerId) {
		Optional<Customer> optional = repo.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Customer deleteCustomerById(int customerId) {
		Optional<Customer> optional = repo.findById(customerId);
		if(optional.isPresent()) {
			Customer customer = optional.get();
			repo.delete(customer);
			return customer;
		}
		return null;
	}
	
	public Customer findCustomerByEmail(String email) {
		Optional<Customer> optional = repo.findByEmail(email);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
