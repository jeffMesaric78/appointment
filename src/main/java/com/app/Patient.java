package com.app;

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
//@Document(collection = "users")
public class Patient {
	@Id
	private String id;
	private String username;
	private String email;
	private String password;
}
