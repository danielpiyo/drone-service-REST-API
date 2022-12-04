package com.example.drone.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drone.Model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long>{

	List<Medication> findAll();

	Optional<Medication> findByMedicationCode(String medicationCode);
	
	List<Medication> findBySerialNumber(String serialNumber);
	
	
}
