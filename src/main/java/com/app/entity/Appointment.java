package com.app.entity;

import jakarta.persistence.Entity;
//import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "appointmentdata")
public class Appointment {
	@Id
	private String id;
	
	private String userName;
	private String email;
	private String phone;
	private String address;
	private String appointmentId;
	private String serviceType;
	private String appointmentDate;
	private String appointmentDuration;
	private String status;
}
