package com.jsp.OnlinePharmacy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.BookingDao;
import com.jsp.OnlinePharmacy.dao.CustomerDao;
import com.jsp.OnlinePharmacy.dao.MedicineDao;
import com.jsp.OnlinePharmacy.dto.BookingDto;
import com.jsp.OnlinePharmacy.entity.Booking;
import com.jsp.OnlinePharmacy.entity.Customer;
import com.jsp.OnlinePharmacy.entity.Medicine;
import com.jsp.OnlinePharmacy.enums.BookingStatus;
import com.jsp.OnlinePharmacy.exception.BookingAlreadyCancelled;
import com.jsp.OnlinePharmacy.exception.BookingCannotBeCancelledNow;
import com.jsp.OnlinePharmacy.exception.BookingDeliveredException;
import com.jsp.OnlinePharmacy.exception.BookingIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.CustomerIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.MedicineIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class BookingService {
	@Autowired
	public BookingDao bookingDao;
	@Autowired
	public CustomerDao customerDao;
	@Autowired
	public MedicineDao medicineDao;
	@Autowired
	public ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Booking>> addBooking(int customerId, int medicineId,
			BookingDto bookingsDto) {
		Booking booking = this.modelMapper.map(bookingsDto, Booking.class);
		Customer dbCustomer = customerDao.getCustomerById(customerId);
		if (dbCustomer != null) { 																	 // customer is present
			Medicine dbMedicine = medicineDao.getMedicineById(medicineId);
			if (dbMedicine != null) { 																// medicine is present
				List<Medicine> medicines = new ArrayList<Medicine>();
				medicines.add(dbMedicine);
				booking.setCustomer(dbCustomer);
				booking.setMedicines(medicines); 													// update customer also
				List<Booking> bookings = new ArrayList<Booking>();
				bookings.add(booking);
				dbCustomer.setBookings(bookings);
				customerDao.updateCustomer(dbCustomer, customerId); 								// updating stock quantity
				dbMedicine.setStockQuantity(dbMedicine.getStockQuantity() - booking.getQuantity()); // adding booking																									
				booking.setBookingStatus(BookingStatus.ACTIVE);									    // status
				Booking dbBooking = bookingDao.saveBooking(booking);

				ResponseStructure<Booking> structure = new ResponseStructure<Booking>();
				structure.setMessage("Booking added successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbBooking);
				return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.CREATED);
			} else {
				throw new MedicineIdNotFoundException("Sorry failed to add booking");
			}

		} else {
			throw new CustomerIdNotFoundException("Sorry failed to add booking");
		}
	}

	public ResponseEntity<ResponseStructure<Booking>> cancelBooking(int bookingId) {
		Booking dbBooking = bookingDao.findBookingById(bookingId);
//		 Expected date=24
//		 cannotBeCancelledDate = 24-2 = 22;

		if (dbBooking != null) {
			LocalDate cantcancelledday = dbBooking.getExpectedDate().minusDays(2);
			if (dbBooking.getBookingStatus().equals(BookingStatus.CANCELLED)) {
				throw new BookingAlreadyCancelled("sorry this booking already Cancelled");
			} else if (dbBooking.getBookingStatus().equals(BookingStatus.DELIVERED)) {
				throw new BookingDeliveredException("Sorry cant cancel Booking its already delivered");
			} else if (LocalDate.now().equals(cantcancelledday) || LocalDate.now().isAfter(cantcancelledday)) {
				throw new BookingCannotBeCancelledNow("Sorry booking cant cancelled Now");
			} else {
				Booking cancelledBooking = bookingDao.cancelBooking(bookingId);
				ResponseStructure<Booking> structure = new ResponseStructure<Booking>();
				structure.setMessage("Booking cancelled Successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(cancelledBooking);
				return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.OK);
			}
		} else {
			throw new BookingIdNotFoundException("Sorry failed to cancel booking");
		}
	}

}
