package com.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
//import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "admins")
public class Admin {
	@Id
	private String id;
	private String username;
	private String email;
	private String password;
}
