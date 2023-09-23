package com.jsp.OnlinePharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.OnlinePharmacy.entity.Medicine;

public interface MedicineRepo extends JpaRepository<Medicine, Integer> {

}
