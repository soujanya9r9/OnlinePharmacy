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

import com.jsp.OnlinePharmacy.entity.Medicine;
import com.jsp.OnlinePharmacy.service.MedicineService;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
	
	@Autowired
	private MedicineService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Medicine>> addMedicine(@RequestParam int storeId, @RequestBody Medicine medicine){
	return service.addMedicine(storeId, medicine);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(@RequestParam int medicineId, @RequestBody Medicine medicine){
		return service.updateMedicine(medicineId, medicine);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Medicine>> getMedicineById(@RequestParam int medicineId){
		return service.getMedicineById(medicineId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicineById(@RequestParam int medicineId){
		return service.deleteMedicineById(medicineId);
	}
}
