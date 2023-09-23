package com.jsp.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.AdminDao;
import com.jsp.OnlinePharmacy.dao.MedicalStoreDao;
import com.jsp.OnlinePharmacy.dao.StaffDao;
import com.jsp.OnlinePharmacy.dto.AdminDto;
import com.jsp.OnlinePharmacy.dto.MedicalStoreDto;
import com.jsp.OnlinePharmacy.dto.StaffDto;
import com.jsp.OnlinePharmacy.entity.Admin;
import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.entity.Staff;
import com.jsp.OnlinePharmacy.exception.AdminIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.StaffIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class StaffService {
	@Autowired
	private StaffDao staffDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MedicalStoreDao medicalStoreDao;
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<StaffDto>> addStaff(int adminId, int storeId, Staff staff) {
		MedicalStore dbMedicalStore = medicalStoreDao.getMedicalStoreById(storeId);
		if (dbMedicalStore != null) {
			staff.setMedicalStore(dbMedicalStore);

			Admin dbAdmin = adminDao.findAdmin(adminId);
			if (dbAdmin != null) {
				staff.setAdmin(dbAdmin);
				Staff dbstaff = staffDao.saveStaff(staff);
				StaffDto dbStaffDto = this.modelMapper.map(dbstaff, StaffDto.class);
				dbStaffDto.setAdminDto(this.modelMapper.map(dbstaff.getAdmin(), AdminDto.class));
				dbStaffDto.setMedicalStoreDto(this.modelMapper.map(dbstaff.getMedicalStore(), MedicalStoreDto.class));

				ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
				structure.setMessage("Staff Added Successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbStaffDto);
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.CREATED);
			} else {
				throw new AdminIdNotFoundException("Sorry Failed to add staff");
			}
		} else {
			throw new MedicalStoreIdNotFoundException("Sorry Failed to add staff");
		}
	}
	
	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff){
		Staff dbStaff = staffDao.updateStaff(staffId, staff);
		if(dbStaff != null) {
			StaffDto dbstaffDto = this.modelMapper.map(dbStaff, StaffDto.class);
			dbstaffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
			dbstaffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
			
			ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
			structure.setMessage("Staff Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbstaffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.OK);
		}
		else {
			throw new StaffIdNotFoundException("Sorry Failed to Update Staff");
		}
	}
	
	public ResponseEntity<ResponseStructure<StaffDto>> getStaffById(int staffId){
		Staff dbstStaff = staffDao.getStaffById(staffId);
		if(dbstStaff != null) {
			StaffDto dbStaffDto = this.modelMapper.map(dbstStaff, StaffDto.class);
			dbStaffDto.setAdminDto(this.modelMapper.map(dbstStaff.getAdmin(), AdminDto.class));
			dbStaffDto.setMedicalStoreDto(this.modelMapper.map(dbstStaff.getMedicalStore(), MedicalStoreDto.class));
			
			ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
			structure.setMessage("Staff Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbStaffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.FOUND);
		}
		else {
			throw new StaffIdNotFoundException("Sorry Cannont Find Staff");
		}
	}
	
	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaffById(int staffId){
		Staff dbStaff = staffDao.deleteStaffById(staffId);
		if(dbStaff != null) {
			StaffDto dbStaffDto = this.modelMapper.map(dbStaff, StaffDto.class);
			dbStaffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
			dbStaffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
			
			ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
			structure.setMessage("Staff Deleted Successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(dbStaffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.GONE);
		}
		else {
			throw new StaffIdNotFoundException("Sorry Failed to Delete Staff");
		}
	}
	
}
