package com.app.entity;

import jakarta.persistence.*;
//import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "users")
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment
	//@Column(name = "id", updatable = false, nullable = false)
	private Integer id;

	private String username;
	private String email;
	private String password;
}
