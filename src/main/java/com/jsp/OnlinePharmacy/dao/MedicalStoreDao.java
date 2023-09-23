package com.jsp.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.repository.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {

	@Autowired
	private MedicalStoreRepo repo;

	public MedicalStore saveMedicalStore(MedicalStore medicalStore) {
		return repo.save(medicalStore);
	}

	public MedicalStore updateMedicalStore(int storeId, MedicalStore medicalStore) {
		Optional<MedicalStore> optional = repo.findById(storeId);	//medicalStore = name, managerName, address
		if (optional.isPresent()) {
			MedicalStore oldMedicalStore = optional.get();
			medicalStore.setStoreId(storeId);	//medicalStore = id, name, managerName, address
			medicalStore.setAdmin(oldMedicalStore.getAdmin());	
			medicalStore.setAddress(oldMedicalStore.getAddress());	//medicalStore is having id, name, managerName, phone, adminAddress
			return repo.save(medicalStore);
		}
		return null;
	}

	public MedicalStore getMedicalStoreById(int storeId) {
		if (repo.findById(storeId).isPresent()) {
			return repo.findById(storeId).get();
		}
		return null;
	}

	public MedicalStore deleteMedicalStoreById(int storeId) {
		Optional<MedicalStore> optional = repo.findById(storeId);
		if(optional.isPresent()) {
			optional.get();
			MedicalStore medicalStore = optional.get();
			repo.delete(medicalStore);
			return medicalStore;
		}
		return null;
	}
}
