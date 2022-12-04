package com.example.drone.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Drone implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long droneId;
	@Column(unique=true)
	private String serialNumber;
	private String model;
	private Long weightLimit;
	private Integer betteryCapacity;
	@Column(updatable = true)
	private String state;

//	Constructor 
	public Drone() {
	}

	public Drone(Long droneId, String SerialNumber, String model, Long weightLimit, Integer betteryCapacity,
			String state) {
		super();
		this.droneId = droneId;
		this.serialNumber = SerialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.betteryCapacity = betteryCapacity;
		this.state = state;
	}

// Getters and Setters
	/**
	 * @return the drone_id
	 */
	public Long getDroneId() {
		return droneId;
	}

	/**
	 * @param drone_id the drone_id to set
	 */
	public void setDrone_id(Long droneId) {
		this.droneId = droneId;
	}

	/**
	 * @return the serial_number
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serial_number the serial_number to set
	 */
	public void setSerial_number(String SerialNumber) {
		this.serialNumber = SerialNumber;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the weight_limit
	 */
	public Long getWeightLimit() {
		return weightLimit;
	}

	/**
	 * @param weight_limit the weight_limit to set
	 */
	public void setWeightLimit(Long weightLimit) {
		this.weightLimit = weightLimit;
	}

	/**
	 * @return the bettery_capacity
	 */
	public Integer getBetteryCapacity() {
		return betteryCapacity;
	}

	/**
	 * @param bettery_capacity the bettery_capacity to set
	 */
	public void setBetteryCapacity(Integer betteryCapacity) {
		this.betteryCapacity = betteryCapacity;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
	@Override
	public String toString() {
		return "Drone [droneId=" + droneId + ", SerialNumber=" + serialNumber + ", model=" + model
				+ ", weightLimit=" + weightLimit + ", betteryCapacity=" + betteryCapacity + ", state=" + state
				+ "]";
	}


}
