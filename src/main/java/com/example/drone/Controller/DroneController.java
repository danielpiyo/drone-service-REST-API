package com.example.drone.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.drone.Model.Drone;
import com.example.drone.Model.Medication;
import com.example.drone.Repository.DroneRepository;
import com.example.drone.Repository.MedicationRepository;
import com.sun.istack.NotNull;


@Controller
@RequestMapping("/api")

public class DroneController {

	@Autowired
	DroneRepository droneRepository;
	
	@Autowired
	MedicationRepository medicationRepository;
	
//	preloading data into H2

	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	        droneRepository.save(new Drone(null, "D-K-00-56MO1", "Auto-Model1", (long) 200, 80, "IDLE"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO2", "Auto-Model1", (long) 350, 60, "LOADED"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO3", "Auto-Model1", (long) 420, 20, "IDLE"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO7", "Auto-Model4", (long) 200, 80, "IDLE"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO8", "Auto-Model3", (long) 400, 14, "LOADED"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO9", "Auto-Model3", (long) 120, 20, "IDLE"));
	        
	        medicationRepository.save(new Medication(null, "MED-002", null, "OXIBRO", (long) 50, null));
	        medicationRepository.save(new Medication(null, "MED-003", null, "PARACETAMOL", (long) 750, null));
	        medicationRepository.save(new Medication(null, "MED-004",null, "AURGMENTINE", (long) 30, null));
	        medicationRepository.save(new Medication(null, "MED-008", null, "CAKE MAX", (long) 50, null));
	        medicationRepository.save(new Medication(null, "MED-013", null, "PIAJET", (long) 600, null));
	        medicationRepository.save(new Medication(null, "MED-0034", null, "MALARIAQUINE", (long) 30, null));
	      };
	   }
	
	
//	Pulling all available drones
	@GetMapping(path = "/allDrones", produces = "application/json")
	public ResponseEntity<List<Drone>> getAllDrones(@RequestParam(required = false) String serialNumber) {

		try {
			List<Drone> drones = new ArrayList<Drone>();

			if (serialNumber == null) {
				droneRepository.findAll().forEach(drones::add);
			} else {
				droneRepository.findByserialNumber(serialNumber);
			}

			if (drones.isEmpty()) {
				throw new RuntimeException("No Data Found");
			}
			return new ResponseEntity<>(drones, HttpStatus.OK);
		} catch (Exception error) {
			error.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	pulling Drone by serial number
	@GetMapping(path = "/drones/{serialNumber}", produces = "application/json")
	public ResponseEntity<Drone> getDroneByNumber(@PathVariable("serialNumber") String serialNumber) {
		Optional<Drone> data = droneRepository.findByserialNumber(serialNumber);

		if (data.isPresent()) {
			return new ResponseEntity<>(data.get(), HttpStatus.OK);
		} else {
			throw new RuntimeException("Sorry No Drone found with Serial number" + serialNumber);
		}
	}

	
//	Registering new drone
	@PostMapping(path = "/registerDrone", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Drone> addDrone(@NotNull @RequestBody Drone drone) {
		try {
			Drone _data = droneRepository.save(drone);
			return new ResponseEntity<>(_data, HttpStatus.CREATED);
		} catch (Exception error) {
			error.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
//	Getting the state of drone (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING)
	@GetMapping(path="/drones/state", produces = "application/json")
	public ResponseEntity<List<Drone>> findDroneByState(@PathVariable("state") String state) {

		try {
			List<Drone> drones = droneRepository.findByState(state);
			if (drones.isEmpty()) {
				throw new RuntimeException("No Drones found for now");
			} else {
				return new ResponseEntity<>(drones, HttpStatus.OK);
			}
		} catch (Exception error) {
			error.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	
//	Pulling available drones for loading state IDLE
	@GetMapping(path = "/drones/available", produces = "application/json")
	public ResponseEntity<List<Drone>> findDroneAvilable() {
		try {
			List<Drone> drones = droneRepository.findByState("IDLE");
			if (drones.isEmpty()) {
				throw new RuntimeException("No Drones found for now. Check in After sometimes");
			} else {
				return new ResponseEntity<>(drones, HttpStatus.OK);
			}
		} catch (Exception error) {
			error.printStackTrace(); 
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
//	Getting drone battery level per sirealNumber
	@GetMapping(path = "/drone/battery/{serialNumber}", produces = "application/json")
	public ResponseEntity<Integer> getBetteryLevel(@PathVariable("serialNumber") String serialNumber) {
		Optional<Drone> _data = droneRepository.findByserialNumber(serialNumber);
		if (_data.isPresent()) {
			Drone _droneData = _data.get();
			return new ResponseEntity<Integer>(_droneData.getBetteryCapacity(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

}
