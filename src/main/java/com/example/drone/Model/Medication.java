package com.example.drone.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medication implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)	
	private Long medicationId;
	@Column(unique=true)
	private String medicationCode;
	@Column(nullable = true, updatable = true)
	private String serialNumber;
	private String medicationName;
	private Long medicationWeight;
	@Column(nullable = true, updatable = false)
	private String image;
	
//	constructor
	public Medication() {
		super();
	}

public Medication(Long medicationId, String medicationCode, String serialNumber, String medicationName, Long medicationWeight,
		String image) {
	super();
	this.medicationId = medicationId;
	this.medicationCode = medicationCode;
	this.serialNumber = serialNumber;
	this.medicationName = medicationName;
	this.medicationWeight = medicationWeight;
	this.image = image;
}

/**
 * @return the medication_id
 */
public Long getMedicationId() {
	return medicationId;
}

/**
 * @param medication_id the medication_id to set
 */
public void setMedication_id(Long medicationId) {
	this.medicationId = medicationId;
}

/**
 * @return the medication_code
 */
public String getMedicationCode() {
	return medicationCode;
}

/**
 * @param medication_code the medication_code to set
 */
public void setMedication_code(String medicationCode) {
	this.medicationCode = medicationCode;
}

/**
 * @return the serialNumber
 */
public String getSerialNumber() {
	return serialNumber;
}

/**
 * @param serialNumber the serialNumber to set
 */
public void setSerialNumber(String serialNumber) {
	this.serialNumber = serialNumber;
}

/**
 * @return the medication_name
 */
public String getMedicationName() {
	return medicationName;
}

/**
 * @param medication_name the medication_name to set
 */
public void setMedication_name(String medicationName) {
	this.medicationName = medicationName;
}

/**
 * @return the medication_weight
 */
public Long getMedicationWeight() {
	return medicationWeight;
}

/**
 * @param medication_weight the medication_weight to set
 */
public void setMedication_weight(Long medicationWeight) {
	this.medicationWeight = medicationWeight;
}

/**
 * @return the image
 */
public String getImage() {
	return image;
}

/**
 * @param image the image to set
 */
public void setImage(String image) {
	this.image = image;
}

@Override
public String toString() {
	return "Medication [medicationId=" + medicationId + ", medicationCode=" + medicationCode + ", serialNumber="
			+ serialNumber + ", medicationName=" + medicationName + ", medicationWeight=" + medicationWeight
			+ ", image=" + image + "]";
}
	
	




		  	  	  	
}
