package com.example.drone;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.drone.Model.Drone;
import com.example.drone.Model.Medication;
import com.example.drone.Repository.DroneRepository;
import com.example.drone.Repository.MedicationRepository;

@SpringBootApplication
public class Drone1Application {
	@Autowired
	DroneRepository droneRepository;
	@Autowired
	MedicationRepository medicationRepository;

	public static void main(String[] args) {
		SpringApplication.run(Drone1Application.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	        droneRepository.save(new Drone(null, "D-K-00-56MO1", "Auto-Model1", (long) 200, 80, "IDLE"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO2", "Auto-Model1", (long) 350, 60, "LOADED"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO3", "Auto-Model1", (long) 420, 20, "IDLE"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO7", "Auto-Model4", (long) 200, 80, "IDLE"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO8", "Auto-Model3", (long) 400, 14, "LOADED"));
	        droneRepository.save(new Drone(null, "D-K-00-56MO9", "Auto-Model3", (long) 120, 20, "IDLE"));
	        
	        medicationRepository.save(new Medication(null, "MED-002", "OXIBRO", null, (long) 50, null));
	        medicationRepository.save(new Medication(null, "MED-003", "PARACETAMOL", null, (long) 750, null));
	        medicationRepository.save(new Medication(null, "MED-004", "AURGMENTINE", null, (long) 30, null));
	        medicationRepository.save(new Medication(null, "MED-008", "CAKE MAX", null, (long) 50, null));
	        medicationRepository.save(new Medication(null, "MED-013", "PIAJET", null, (long) 600, null));
	        medicationRepository.save(new Medication(null, "MED-0034", "MALARIAQUINE", null, (long) 30, null));
	      };
	   }
	

}
