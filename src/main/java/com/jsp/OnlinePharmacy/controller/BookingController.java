package com.jsp.OnlinePharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlinePharmacy.dto.BookingDto;
import com.jsp.OnlinePharmacy.entity.Booking;
import com.jsp.OnlinePharmacy.service.BookingService;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	public BookingService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> addBooking(@RequestParam int customerId, @RequestParam int medicineId ,@RequestBody BookingDto bookingDto){
		return service.addBooking(customerId,medicineId,bookingDto);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Booking>>  cancelBooking(@RequestParam int bookingId){
		return service.cancelBooking(bookingId);
	}
}
