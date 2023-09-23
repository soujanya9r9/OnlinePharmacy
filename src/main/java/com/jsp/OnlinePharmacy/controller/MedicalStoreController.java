package com.jsp.OnlinePharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlinePharmacy.dto.MedicalStoreDto;
import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.service.MedicalStoreService;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/store")
public class MedicalStoreController {

	@Autowired
	private MedicalStoreService service;
	
	@PostMapping("/{addressId}/{adminId}")
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalStore(@PathVariable int adminId, @PathVariable int addressId, @RequestBody MedicalStoreDto medicalStoreDto){
		return service.saveMedicalStore(adminId, addressId, medicalStoreDto);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(@RequestParam int storeId, @RequestBody MedicalStore medicalStore){
		return service.updateMedicalStore(storeId, medicalStore);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStoreById(@RequestParam int storeId){
		return service.getMedicalStoreById(storeId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStoreById(@RequestParam int storeId){
		return service.deleteMedicalStoreById(storeId);
	}
}
