package com.example.drone.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.drone.Model.Drone;
import com.example.drone.Model.Medication;
import com.example.drone.Repository.DroneRepository;
import com.example.drone.Repository.MedicationRepository;

@Controller
@RequestMapping("/api")
public class MedicationController {

	@Autowired
	MedicationRepository medicationRepository;
	@Autowired
	DroneRepository droneRepository;

//	pulling all medications available
	@GetMapping(path="/allMedications", consumes="application/json")
	public ResponseEntity<List<Medication>> getAllMedications(@RequestParam(required = false) String medicationCode) {
		try {
			List<Medication> medication = new ArrayList<Medication>();
			if (medicationCode == null) {
				medicationRepository.findAll().forEach(medication::add);
			} else {
				medicationRepository.findByMedicationCode(medicationCode);
			}
			if (medication.isEmpty()) {
				throw new RuntimeException("Sorry There are No Medication for now");
			}
			return new ResponseEntity<>(medication, HttpStatus.OK);

		} catch (Exception error) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//pulling Medications Loaded to particular drone 
	@GetMapping(path="/medication/drone/{serialNumber}", consumes="application/json")
	public ResponseEntity<List<Medication>> getMedicationForDrone(@PathVariable("serialNumber") String serialNumber) {
		List<Medication> medication = medicationRepository.findBySerialNumber(serialNumber);
		if (medication.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(medication);
		}
	}
	
	
// registering new medication 
	@PostMapping(path="/newMedication", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Medication> addMedication(@RequestBody Medication medication) {
		try {
			Medication _data = medicationRepository.save(medication);
			return new ResponseEntity<>(_data, HttpStatus.CREATED);
		} catch (Exception error) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
//	Loading the medication to available drone. Done by updating the existing data in the medication table
	@PutMapping(path="/medication/load/{medicationCode}", consumes = "application/json", produces = "application/json" )
	public ResponseEntity<Medication> loadDrone(@PathVariable("medicationCode") String medicationCode,
			@RequestBody(required = true) Drone drone) {
		try {
			Optional<Medication> _medicationData = medicationRepository.findByMedicationCode(medicationCode);
			Optional<Drone> _droneData = droneRepository.findByserialNumber(drone.getSerialNumber());
			if (_medicationData.isPresent() && _droneData.isPresent()) {
				Medication _med = _medicationData.get();
				Drone _drn = _droneData.get();
				if (_med.getMedicationWeight() <= _drn.getWeightLimit()  && _drn.getBetteryCapacity() > 25) {
					_med.setSerialNumber(drone.getSerialNumber());
					_drn.setState("LOADED");
				} else {
					throw new RuntimeException("Sorry your Medication wight or Drone Battery level Not standard");
				}
				return new ResponseEntity<>(medicationRepository.save(_med), HttpStatus.OK);
			} else {
				throw new RuntimeException("Sorry your Medication with code: " + medicationCode + "No longer in stock");
			}

		} catch (Exception error) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
