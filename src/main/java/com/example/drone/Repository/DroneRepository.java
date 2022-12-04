package com.example.drone.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.drone.Model.Drone;

public interface DroneRepository extends JpaRepository<Drone, Long>{
	
	List<Drone> findAll();
	
	List<Drone> findByState(String state);
	
//	Drone findBetryLevel(String serial_number);
	
	Optional<Drone> findByserialNumber(String serialNumber);

}
