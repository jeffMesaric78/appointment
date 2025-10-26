package com.app;

//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import jakarta.persistence.Entity;
import org.springframework.data.repository.CrudRepository;

//@Repository
//@EnableMongoRepositories
public interface UserRepo extends CrudRepository<Patient, String> {

	Patient findByEmail(String email);

	Patient findByEmailAndPassword(String email, String password);

}
