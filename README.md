#" drone-service-REST-API" 
[[_TOC_]]

---

:scroll: **START**


### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has: 
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task. 

The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;

> Feel free to make assumptions for the design approach. 

---

### Requirements

While implementing your solution **please take care of the following requirements**: 

#### Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

---

#### Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

---

### How to build

#### Requirements

- Java 8
- Java IDE (STS)
- MYSQL databse (Optional you can use in-memory database)
- Postman(For testing ) 

### Steps by step for building and running the project locally

- You can Clone this project from gitlab using this command git clone https://oauth:glpat-2g_hezzv3yxrpmPeE49T@gitlab.com/musala-coding-tasks-solutions/daniel-opiyo.git

![images/img_8.png](images/img_8.png)
- Open the cloned project in Spring Boot Suite 4 or any other  of your preference

- Go to maven the update Project to update all the maven dependencies

- Maven Build the project and run

- Before running, you can run the JUnit test cases to assert that everything is working correctly (I have included some of the JUnit tests)

### Testing the API
Open Postman
![images/img_9.png](images/img_9.png)
----

- **Registering a drone** localhost:8080/api/registerDrone
The payload and response should be in json format like this

![images/img_7.png](images/img_7.png)
**Checking available drones for loading;**


Before loading a drone with Medication you can first check the available drones

**localhost:8080/api/drones/available**

![images/img_6.png](images/img_6.png)
---

- **Loading a drone with medication items;**

**localhost:8080/api/medication/load/{MedicationCode}**

![images/img_3.png](images/img_3.png)
The payload will have the following fields

- serialNumber is the unique serial for the drone being loaded from RequestBody
- medicationCode is the unique code for the medication load being loaded to the drone. Its is passed as a path variable
- We update the Medication table and set the serialNumber column to the serial number of the drone

N/B For this case we don't have the source/destination of the medication

N/B For a medication o be loaded to a drone they both must meet the set standards
- Drone not to be loaded with more weight that it can carry
- Drone Battery not to be less than 25%

the Medication items to be loaded for testing are code : **MED-002, MED-003, MED-004, MED-008, MED-013** (Passed as a path variable)

the Drones to be loaded for testing are of serial numbers : **D-K-00-56MO1, D-K-00-56MO2, D-K-00-56MO3, D-K-00-56MO7, D-K-00-56MO9**  (passed in the request body)


---
- **Checking loaded medication items for a given drone;**

**localhost:8080/api/medication/drone/{serialNumber}**

- Check which medication item is loaded to a specific drone.

![images/img_4.png](images/img_4.png)

- **Check drone battery level for a given drone;**

**localhost:8080/api/drone/battery/{serialNumber}**

![images/img_5.png](images/img_5.png)
---
:scroll: **END** 
