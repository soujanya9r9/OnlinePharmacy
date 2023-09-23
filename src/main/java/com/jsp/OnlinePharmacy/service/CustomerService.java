package com.jsp.OnlinePharmacy.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.AddressDao;
import com.jsp.OnlinePharmacy.dao.CustomerDao;
import com.jsp.OnlinePharmacy.dto.AddressDto;
import com.jsp.OnlinePharmacy.dto.BookingDto;
import com.jsp.OnlinePharmacy.dto.CustomerDto;
import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.entity.Booking;
import com.jsp.OnlinePharmacy.entity.Customer;
import com.jsp.OnlinePharmacy.exception.AddressAlreadyMappedToOtherCustomer;
import com.jsp.OnlinePharmacy.exception.AddressIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.CustomerIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.EmailNotFoundException;
import com.jsp.OnlinePharmacy.exception.InvalidPasswordException;
import com.jsp.OnlinePharmacy.exception.PhoneNumberNotValidException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(int addressId, Customer customer) {
		Address dbAddress = addressDao.findAddressById(addressId);
		if (dbAddress != null) { // Checking addressId is present or not.
			if (dbAddress.getCustomer() != null) {
				throw new AddressAlreadyMappedToOtherCustomer("Sorry");
			}
			dbAddress.setCustomer(customer);
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(dbAddress);
			customer.setAddresses(addresses);
			Customer dbCustomer = customerDao.saveCustomer(customer);
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;

			for (Address address : dbCustomer.getAddresses()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos = new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
			}
			customerDto.setBookingsDtos(null);
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer Saved Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbCustomer);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new AddressIdNotFoundException("Sorry Failed to Add Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(Customer customer, int customerId) {
		Customer dbCustomer = customerDao.updateCustomer(customer, customerId);
		if (dbCustomer != null) {
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
			for (Address address : dbCustomer.getAddresses()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos = new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingsDtos(null);
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer Added Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
		} else {
			throw new CustomerIdNotFoundException("Sorry Failed to Update Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerById(int customerId) {
		Customer dbCustomer = customerDao.getCustomerById(customerId);
		if (dbCustomer != null) {
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
			for (Address address : dbCustomer.getAddresses()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos = new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingsDtos(null);
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new CustomerIdNotFoundException("Sorry Failed to Fetch Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomerById(int customerId) {
		Customer dbCustomer = customerDao.deleteCustomerById(customerId);
		if (dbCustomer != null) {
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
			for (Address address : dbCustomer.getAddresses()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos = new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingsDtos(null);
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer Deleted Successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.GONE);
		} else {
			throw new CustomerIdNotFoundException("Sorry Failed to Delete Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(String email, String password) {
		Customer dbCustomer = customerDao.findCustomerByEmail(email);
		if (dbCustomer != null) {
			if (dbCustomer.getPassword().equals(password)) {//	login success
				CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
				List<AddressDto> addressDtos;
				for (Address address : dbCustomer.getAddresses()) {
					AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
					addressDtos = new ArrayList<AddressDto>();
					addressDtos.add(addressDto);
					customerDto.setAddresses(addressDtos);
				}
				List<BookingDto> bookingDtos;
				for (Booking booking : dbCustomer.getBookings()) {
					BookingDto bookingDto = this.modelMapper.map(booking, BookingDto.class);
					bookingDtos = new ArrayList<BookingDto>();
					bookingDtos.add(bookingDto);
					customerDto.setBookingsDtos(bookingDtos);
				}

				ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
				structure.setMessage("Customer Login Success WELCOME TO ONLINEPHARMACY");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(customerDto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
			} else {//	invalid password
				throw new InvalidPasswordException("Sorry failed to Login");
			}

		} else {
			throw new EmailNotFoundException("Sorry Failed to Login");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> forgotPassword(String email, long phone, String password) {
		Customer customer = customerDao.findCustomerByEmail(email);
		if (customer != null) { // customer is present
			if (customer.getPhoneNumber() == phone) { // phone is registered
				customer.setPassword(password);
				Customer dbCustomer = customerDao.updateCustomer(customer, customer.getCustomerId());
				CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
				List<AddressDto> addressDtos;
				for (Address address : dbCustomer.getAddresses()) {
					AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
					addressDtos = new ArrayList<AddressDto>();
					addressDtos.add(addressDto);
					customerDto.setAddresses(addressDtos);
				}
				List<BookingDto> bookingDtos;
				for (Booking booking : dbCustomer.getBookings()) {
					BookingDto bookingDto = this.modelMapper.map(booking, BookingDto.class);
					bookingDtos = new ArrayList<BookingDto>();
					bookingDtos.add(bookingDto);
					customerDto.setBookingsDtos(bookingDtos);
				}

				ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
				structure.setMessage("Customer Pssword reset successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(customerDto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);

			} else {
				throw new PhoneNumberNotValidException(
						"Sorry failed to reset password Please enter Registered PhoneNumber");
			}
		} else {
			throw new EmailNotFoundException("Sorry failed to reset password Please enter valid Email");
		}
	}
}
