package com.jsp.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.OnlinePharmacy.entity.Booking;
import com.jsp.OnlinePharmacy.enums.BookingStatus;
import com.jsp.OnlinePharmacy.repository.BookingRepo;
@Repository
public class BookingDao {
	@Autowired
	public BookingRepo repo;
	
	public Booking saveBooking(Booking booking) {
		return repo.save(booking);
	}
	
	public Booking cancelBooking(int bookingId) {
		Optional<Booking> optional = repo.findById(bookingId);
		if(optional.isPresent()) {
			Booking booking = optional.get();
			booking.setBookingStatus(BookingStatus.CANCELLED);
			return repo.save(booking);
		}
		return null;
	}
	
	public Booking findBookingById(int bookingId) {
		Optional<Booking> optional = repo.findById(bookingId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
