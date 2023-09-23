package com.jsp.OnlinePharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestControllerAdvice
public class OnlinePharmacyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressIdNotFoundException(AddressIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Address Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressIdNotFoundException(InvalidPasswordException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Sorry Incorrect Password");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminIdNotFoundException(AdminIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Admin Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicalStoreIdNotFoundException(MedicalStoreIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("MedicalStore Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicineIdNotFoundException(MedicineIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Medicine Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> staffIdNotFoundException(StaffIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Staff Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressAlreadyMappedToOtherCustomerException(AddressAlreadyMappedToOtherCustomer ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Address Already Mapped to other Customer");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressAlreadyMappedToCustomerException(AddressAlreadyMappedToCustomer ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Address Already Mapped to Customer");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressAlreadyMappedToMedicalStoreException(AddressMappedToMedicalStore ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Address Already Mapped to MedicalStore");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerIdNotFoundException(CustomerIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Customer Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> phoneNotFoundException(PhoneNumberNotValidException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Sorry Phone Number is Not Registered");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> emailNotFoundException(EmailNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Sorry Email is Not Present");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingAlreadyCancelldException(BookingAlreadyCancelled  ex){
		 ResponseStructure<String> structure=new  ResponseStructure<String>();
		 structure.setMessage("Booking Already cancelled");
		 structure.setStatus(HttpStatus.NOT_FOUND.value());
		 structure.setData(ex.getMessage());
		 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingalreadyDeliveredException(BookingDeliveredException  ex){
		 ResponseStructure<String> structure=new  ResponseStructure<String>();
		 structure.setMessage("Booking Already Delivered");
		 structure.setStatus(HttpStatus.NOT_FOUND.value());
		 structure.setData(ex.getMessage());
		 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingException(BookingCannotBeCancelledNow  ex){
		 ResponseStructure<String> structure=new  ResponseStructure<String>();
		 structure.setMessage("Booking cannot be Cancelled Now. Order Already Dispatched");
		 structure.setStatus(HttpStatus.NOT_FOUND.value());
		 structure.setData(ex.getMessage());
		 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingaIdNotFoundException(BookingIdNotFoundException  ex){
		 ResponseStructure<String> structure=new  ResponseStructure<String>();
		 structure.setMessage("Booking Id Not Found");
		 structure.setStatus(HttpStatus.NOT_FOUND.value());
		 structure.setData(ex.getMessage());
		 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
}
