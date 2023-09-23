package com.jsp.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.AddressDao;
import com.jsp.OnlinePharmacy.dao.AdminDao;
import com.jsp.OnlinePharmacy.dao.MedicalStoreDao;
import com.jsp.OnlinePharmacy.dto.AddressDto;
import com.jsp.OnlinePharmacy.dto.AdminDto;
import com.jsp.OnlinePharmacy.dto.MedicalStoreDto;
import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.entity.Admin;
import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.exception.AddressIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.AdminIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class MedicalStoreService {
	
	@Autowired
	private MedicalStoreDao storeDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AddressDao addressDao;
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalStore(int adminId, int adddressId, MedicalStoreDto medicalStoreDto){
		MedicalStore medicalStore = this.modelMapper.map(medicalStoreDto, MedicalStore.class);
		//This MedicalStore does not have admin and address yet. So, first admin has to be fetched.
		
		Admin dbAdmin = adminDao.findAdmin(adminId);
		//Checking whether this admin is present or not
		if(dbAdmin != null) {
			medicalStore.setAdmin(dbAdmin);			
			Address dbAddress = addressDao.findAddressById(adddressId);
			if(dbAddress != null) {
				
				medicalStore.setAddress(dbAddress);
				MedicalStore dbMedicalStore = storeDao.saveMedicalStore(medicalStore);
				Address dbMedicalStoreAddress = dbMedicalStore.getAddress();
				AddressDto addressDto = this.modelMapper.map(dbMedicalStoreAddress, AddressDto.class);
				Admin dbMedicalStoreAdmin = dbMedicalStore.getAdmin();
				
				MedicalStoreDto dto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
				dto.setAddressDto(addressDto);
				dto.setAdminDto(this.modelMapper.map(dbMedicalStoreAdmin, AdminDto.class));;
				
				ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
				structure.setMessage("MedicalStore Added Successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.CREATED);
			}
			else {
				throw new AddressIdNotFoundException("Sorry Failed To Save MedicalStore");
			}
		}
		else {
			throw new AdminIdNotFoundException("Sorry Failed To Save MedicslStore");
		}
	}
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId, MedicalStore medicalStore){
		MedicalStore dbMedicalStore = storeDao.updateMedicalStore(storeId, medicalStore);
		if(dbMedicalStore != null) {
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));
			
			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.OK);
		}
		else {
			throw new MedicalStoreIdNotFoundException("Sorry Failed to Update MedicalStore");
		}
	}
	
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> getMedicalStoreById(int storeId){
		MedicalStore dbMedicalStore = storeDao.getMedicalStoreById(storeId);
		if(dbMedicalStore != null) {
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));
			
			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore Fetched Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.FOUND);
		}
		else {
			throw new MedicalStoreIdNotFoundException("Sorry Failed to Fetch MedicalStore");
		}
	}
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStoreById(int storeId){
		MedicalStore dbMedicalStore = storeDao.deleteMedicalStoreById(storeId);
		if(dbMedicalStore != null) {
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));
			
			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore Deleted Successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.GONE);
		}
		else {
			throw new MedicalStoreIdNotFoundException("Sorry Failed to Delete MedicalStore");
		}
	}
}
