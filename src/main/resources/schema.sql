-- A **Drone** has:
-- - serial number (100 characters max);
-- - model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
-- - weight limit (500gr max);
-- - battery capacity (percentage);
-- - state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

DROP TABLE IF EXISTS DRONE;  
CREATE TABLE DRONE (  
droneId BIGINT AUTO_INCREMENT  PRIMARY KEY,  
serialNumber VARCHAR(100) UNIQUE NOT NULL,
model VARCHAR(50) NOT NULL,  
weightLimit INT NOT NULL,  
betteryCapacity INT NOT NULL,
state VARCHAR(30) NOT NULL
); 

-- Each **Medication** has: 
-- - name (allowed only letters, numbers, ‘-‘, ‘_’);
-- - weight;
-- - code (allowed only upper case letters, underscore and numbers);
-- - image (picture of the medication case).

DROP TABLE IF EXISTS MEDICATION;  
CREATE TABLE MEDICATION (  
medicationId BIGINT AUTO_INCREMENT  PRIMARY KEY,  
medicationCode VARCHAR(100) UNIQUE NOT NULL,
serialNumber VARCHAR(100),
medicationName VARCHAR(50) NOT NULL,  
medicationWeight INT NOT NULL,  
image BLOB,
foreign key (serialNumber) references DRONE(serialNumber)
); 

-- DROP TABLE IF EXISTS TRANSACTIONS;
-- CREATE TABLE TRANSACTIONS (
-- transactionId BIGINT AUTO_INCREMENT PRIMARY KEY,
-- serialNumber VARCHAR(100) NOT NULL,
-- medicationCode VARCHAR(100),
-- FOREIGN KEY (serialNumber) references DRONE(serialNumber),
-- FOREIGN KEY (medicationCode) references MEDICATION(medicationCode)
-- );