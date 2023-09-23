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

import com.jsp.OnlinePharmacy.dto.AdminDto;
import com.jsp.OnlinePharmacy.entity.Admin;
import com.jsp.OnlinePharmacy.service.AdminService;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@RequestBody Admin admin){
		return service.saveAdmin(admin);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(@RequestParam int adminId, @RequestBody Admin admin){
		return service.updateAdmin(adminId, admin);
	}
	
//	@GetMapping
//	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(@RequestParam int adminId){
//		return service.findAdmin(adminId);
//	}
//	
//	@DeleteMapping
//	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(@RequestParam int adminId){
//		return service.deleteAdmin(adminId);
//	}
}
