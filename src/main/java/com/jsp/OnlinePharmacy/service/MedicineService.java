package com.jsp.OnlinePharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.MedicalStoreDao;
import com.jsp.OnlinePharmacy.dao.MedicineDao;
import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.entity.Medicine;
import com.jsp.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.MedicineIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class MedicineService {

	@Autowired
	private MedicineDao medicineDao;
	@Autowired
	private MedicalStoreDao storeDao;
	
	public ResponseEntity<ResponseStructure<Medicine>> addMedicine(int storeId, Medicine medicne){
		MedicalStore dbMedicalStore = storeDao.getMedicalStoreById(storeId);
		if(dbMedicalStore != null) {
			medicne.setMedicalStore(dbMedicalStore);
			Medicine dbMedicine = medicineDao.addMedicine(medicne);
			
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine Addedd Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.CREATED);

		}
		throw new MedicalStoreIdNotFoundException("Sorry Failed to Add Medicine");
	}
	
	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(int medicineId, Medicine medicine){
		Medicine dbMedicine = medicineDao.updateMedicine(medicineId, medicine);
		if(dbMedicine != null) {
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.OK);
		}else {
			throw new MedicineIdNotFoundException("Sorry Failed to Update Medicine");
		}
	}
	
	public ResponseEntity<ResponseStructure<Medicine>> getMedicineById(int medicineId){
		Medicine dbMedicine = medicineDao.getMedicineById(medicineId);
		if(dbMedicine != null) {
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine Fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.FOUND);
		}else {
			throw new MedicineIdNotFoundException("Sorry Failed to Fetch Medicine");
		}
	}
	
	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicineById(int medicineId){
		Medicine dbMedicine = medicineDao.deleteMedicineById(medicineId);
		if(dbMedicine != null) {
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine Deleted successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.GONE);
		}else {
			throw new MedicineIdNotFoundException("Sorry Failed to Delete Medicine");
		}
	}
}
